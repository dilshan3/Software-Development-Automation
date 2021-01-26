import torch
import torchtext
import sys


glove = torchtext.vocab.GloVe(name="6B", 
                              dim=100) 


inarray = []
filename=sys.argv[1]

print(sys.argv[1])

filehandle= open(filename, 'r')

while True:
    line = filehandle.readline()
    if not line:
        break
    if len(line)>2:
        inarray.append(line.rstrip("\n").lower())
    
filehandle.close()    
print(inarray)    

secarray=inarray
outputarray=[]
i = 0

file = open(filename, 'w+')

for i in range(len(inarray)):

    word = inarray[i]
    x = glove[word]

    for w in range(len(inarray)):

        if  w > i:

            wrd = inarray[w]
            y= glove[wrd]
            sim = torch.cosine_similarity(x.unsqueeze(0), y.unsqueeze(0))
            outputarray.append(word)
            outputarray.append(float(sim)*100)
            outputarray.append(wrd)
            file.write("%s "%word)
            file.write("%f "%(float(sim)*100))
            file.write("%s\n"%wrd)
    

    
file.close()
