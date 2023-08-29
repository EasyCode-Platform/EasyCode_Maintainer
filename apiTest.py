import requests
import json

url = 'http://localhost:8080/pod/'
data = {'key1': 'value1', 'key2': 'value2'}
response = requests.get(url)

print(response.status_code)
response_json = json.dumps(response.json(), indent=4)
print(response_json)

try:
    message_data = json.loads(response_json['message'])
    formatted_message = json.dumps(message_data, indent=4)
    print(formatted_message)
except KeyError:
    print("No 'message' field found in the response.")
except json.JSONDecodeError:
    print("Failed to decode 'message' field as JSON.")
