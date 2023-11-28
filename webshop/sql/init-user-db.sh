#!/bin/bash
set -e

for script in /docker-entrypoint-initdb.d/*.sql; do
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" -f "$script"
done
