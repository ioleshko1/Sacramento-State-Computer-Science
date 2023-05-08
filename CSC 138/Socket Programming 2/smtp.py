from socket import *

# Create a variable to hold the message and end message
msg = "\r\n I love computer networks!"
endmsg = "\r\n.\r\n"

# Choose a mail server (e.g. Google mail server) and call it mailserver
mailserver = 'smtp.csus.edu'
mailport = 25

# Create socket called clientSocket and establish a TCP connection with mailserver
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((mailserver,mailport))



# Recieve the respose from the server
recv = clientSocket.recv(1024).decode()

# Print the recieved message
print(recv)

# Check to see if the recieved message is not equal to 220
if recv[:3] != '220':
    
    # If it is something other than 220 then we output that we did not recieve a 220 reply
    print('220 reply not received from server.')




# Create a variable to hold the HELO command
heloCommand = 'HELO Alice\r\n'

# Create a client socket and send the encoded HELO command
clientSocket.send(heloCommand.encode())

# Recieve the respose from the server
recv = clientSocket.recv(1024).decode()

# Print the recieved message
print(recv)

# Check to see if the recieved message is not equal to 250
if recv[:3] != '250':
    
    # If it is something other than 250 then we output that we did not recieve a 250 reply
    print('250 reply not received from server.')
 
 
 
 

# Create a variable to hold the MAIL FROM command
mailFromCommand = 'MAIL FROM: igoroleshko1@gmail.com\r\n'

# Create a client socket and send the encoded MAIL FROM command
clientSocket.send(mailFromCommand.encode())

# Recieve the respose from the server
recv = clientSocket.recv(1024).decode()

# Print the recieved message
print(recv)

# Check to see if the recieved message is not equal to 250
if recv[:3] != '250':
    
    # If it is something other than 250 then we output that we did not recieve a 250 reply
    print('250 reply not received from server.')





# Create a variable to hold the RCPT TO command
receiptToCommand = 'RCPT TO: ioleshko@csus.edu\r\n'

# Create a client socket and send the encoded RCPT TO command
clientSocket.send(receiptToCommand.encode())

# Recieve the respose from the server
recv = clientSocket.recv(1024).decode()

# Print the recieved message
print(recv)

# Check to see if the recieved message is not equal to 250
if recv[:3] != '250':
    
    # If it is something other than 250 then we output that we did not recieve a 250 reply
    print('250 reply not received from server.')





# Create a variable to hold the DATA command
dataCommand = 'DATA\r\n'

# Create a client socket and send the encoded DATA command
clientSocket.send(dataCommand.encode())

# Recieve the respose from the server
recv = clientSocket.recv(1024).decode()

# Print the recieved message
print(recv)

# Check to see if the recieved message is not equal to 354
if recv[:3] != '354':
    
    # If it is something other than 354 then we output that we did not recieve a 354 reply
    print('354 reply not received from server.')





# Create a client socket and send the encoded msg data
clientSocket.send(msg.encode())

# Create a client socket and send the encoded endmsg data
clientSocket.send(endmsg.encode())

# Recieve the respose from the server
recv = clientSocket.recv(1024).decode()

# Print the recieved message
print(recv)

# Check to see if the recieved message is not equal to 250
if recv[:3] != '250':
    
    # If it is something other than 250 then we output that we did not recieve a 250 reply
    print('250 reply not received from server.')





# Create a variable to hold the QUIT command
quitCommand = 'QUIT\r\n'

# Create a client socket and send the encoded QUIT command
clientSocket.send(quitCommand.encode())

# Recieve the respose from the server
recv = clientSocket.recv(1024).decode()

# Print the recieved message
print(recv)

# Check to see if the recieved message is not equal to 221
if recv[:3] != '221':
    
    # If it is something other than 221 then we output that we did not recieve a 221 reply
    print('221 reply not received from server.')

