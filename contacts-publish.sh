#!/bin/bash

# Check if the JSON file is provided as an argument
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <authorization token>"
    exit 1
fi

JSON_FILE="contacts.json"

# Check if the file exists
if [ ! -f "$JSON_FILE" ]; then
    echo "Error: File '$JSON_FILE' not found."
    exit 1
fi

# Read and process the JSON array
jq -c '.[]' "$JSON_FILE" | while IFS= read -r item; do
    # Execute the cURL command
    curl -X POST -H "Content-Type: application/json" -H "authorization: Bearer $1" -d "$item" https://demo-sor.fly.dev/api/v1/contacts
done
