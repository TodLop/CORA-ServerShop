#!/usr/bin/env bash
set -euo pipefail

repo_root="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$repo_root"

blocked_markers=(
  "nearoutpost"
  "Near Outpost"
  "ServerAccountAPI"
  "/Users/jang"
  "data/minecraft"
  "logs/latest"
  "DEPLOYMENT_VERIFICATION"
  "DEPLOYMENT_CHECKLIST"
)

for marker in "${blocked_markers[@]}"; do
  if rg -n --hidden --glob '!.git/**' --glob '!scripts/check_public_repo.sh' -- "$marker" .; then
    echo "Blocked marker found: $marker" >&2
    exit 1
  fi
done

if git ls-files | grep -E '(^|/)(target/|.*\.(jar|class)$)' >/dev/null; then
  echo "Tracked build artifact found." >&2
  exit 1
fi

if find . -path './.git' -prune -o \( -name '*.jar' -o -name '*.class' \) -print | grep -q .; then
  echo "Warning: local ignored build artifacts exist; they are not tracked." >&2
fi

if [ -d target ]; then
  echo "Warning: local Maven target directory exists; it is ignored and not tracked." >&2
fi

if git status --short --untracked-files=all | grep -E '(^| )(target/|.*\.(jar|class)$)' >/dev/null; then
  echo "Unignored build artifact found." >&2
  exit 1
fi

if rg -n '^\s*(diamond|diamond_block|price|[0-9]+):\s+[0-9]+\.[0-9]+' src/main/resources/config.yml; then
  echo "Default config contains decimal economy amounts, but runtime validation requires integer amounts." >&2
  exit 1
fi

echo "Public repository check passed."
