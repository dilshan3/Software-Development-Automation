#!/usr/bin/env python
# coding: utf-8

# In[5]:


import docx
import nltk 
import collections
nltk.download('punkt')
nltk.download('averaged_perceptron_tagger')

from pathlib import Path

f = open(".\\src\\main\\java\\com\\myprojcet\\sdaproject\\Component1\\Functionalitiy.txt")
lines = f.read()
is_noun = lambda pos: pos[:2] == 'NN'

tokenized = nltk.word_tokenize(lines)

nouns = [word for (word, pos) in nltk.pos_tag(tokenized) if is_noun(pos)]

counter = collections.Counter(nouns)
#print(counter)

print('-------------------------------------------Class Names-------------------------------------------')
print()
print(counter.most_common(9))
print()

print('-------------------------------------------------------------------------------------------------')


def class_names(nouns):
    counter = 0
    num = nouns[0]
    
    for i in nouns:
        curr_frequency = nouns.count(i)
        if(curr_frequency > counter):
            counter = curr_frequency
            num = i
    return num

#print(nouns)
#print(class_names(nouns))

#writing classnames to text file
listToStr = ''.join([str(elm) for elm in counter.most_common(9)])
file = open(".\\src\\main\\java\\com\\myprojcet\\sdaproject\\Component1\\classNames.txt","w")
file.write(listToStr)
file.close()


# In[ ]:





# In[ ]:




