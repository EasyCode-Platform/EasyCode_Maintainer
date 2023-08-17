import requests

url = 'http://localhost:8080/api/v1/nodes'
data = {'key1': 'value1', 'key2': 'value2'}
response = requests.get(url)

print(response.status_code)
print(response.json())
