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

if find . -path './.git' -prune -o \( -name '*.jar' -o -name '*.class' \) -print | grep -q .; then
  echo "Build artifact found in repository tree." >&2
  exit 1
fi

if find . -path './.git' -prune -o -path './target' -print | grep -q .; then
  echo "Maven target directory found in repository tree." >&2
  exit 1
fi

echo "Public repository check passed."
