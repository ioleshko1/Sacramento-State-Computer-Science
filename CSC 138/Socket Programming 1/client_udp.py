from socket import *

# Capture the serverName which we will use to connect to the socket
serverName = '127.0.0.1'

# Use a random serverPort as long as it matches up with the serverPort in the server_udp.py file
serverPort = 12111

# Create a client socket using the information above
clientSocket = socket(AF_INET, SOCK_DGRAM)

# Take the input that the user has entered
message = input('Input a sentence:')

# Sent the encoded message that the user enetered to the server
clientSocket.sendto(message.encode(),(serverName, serverPort))

# Retrieve the message from the server
modifiedMessage, serverAddress = clientSocket.recvfrom(2048)

# Print the decoded version of the modifiedMessage
print (modifiedMessage.decode())

# Close the connection
clientSocket.close()

