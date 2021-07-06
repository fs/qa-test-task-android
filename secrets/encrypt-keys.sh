#!/bin/bash
if [ -z "$1" ]; then
    echo 'You must provide secret key as first param' >&2
    exit 2
else
    openssl aes-256-cbc -md sha256 -nosalt -a -pass pass:$1 -in secrets/project.properties -out secrets/project.properties.crypted
fi
