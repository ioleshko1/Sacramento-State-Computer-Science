from socket import *

# Capture the serverName which we will use to connect to the socket
serverName = '127.0.0.1'

# Use a random serverPort as long as it matches up with the serverPort in the server_tcp.py file
serverPort = 12113

# Create a client socket
clientSocket = socket(AF_INET, SOCK_STREAM)

# Connect the clientSocket to the serverName and serverPort
clientSocket.connect((serverName,serverPort))

# Take the input that the user has entered
sentence = input('Input a sentence:')

# Send the encoded message that the user enetered to the server
clientSocket.send(sentence.encode())

# Retrieve the message from the server
modifiedSentence = clientSocket.recv(1024)

# Print the decoded version of the modifiedMessage
print('From Server:', modifiedSentence.decode())

# Close the connection
clientSocket.close()

