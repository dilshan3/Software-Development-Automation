#!/usr/bin/env python
# coding: utf-8

# In[12]:


import pandas as pd
import matplotlib.pyplot as plt
from sklearn import linear_model
import sys
import os
import numpy as np

#load dataset
data = pd.read_csv(r'.\\src\\main\\java\\com\\myprojcet\\sdaproject\\Component1\\dataSet_timeforecast.csv')

#view dataset
data

data.plot(kind='scatter', x='Developers', y ='Months')
#plt.show()

#correlation coefficients
data.corr()

#create dataframe variables
developers=pd.DataFrame(data['Developers'])
months=pd.DataFrame(data['Months'])

developers

#build linear regresssion model
lm = linear_model.LinearRegression()
model = lm.fit(developers,months)

#calculate coefficient
model.coef_

#calculate intercept
model.intercept_

model.score(developers,months)

#predicting new value for month

f = open(".\\src\\main\\java\\com\\myprojcet\\sdaproject\\Component1\\Developers number.txt")
lines = f.read()
number = int(lines)
developer_new = np.array(number)
month_predict = model.predict(developer_new.reshape(1,1))
month = np.around(month_predict, decimals=1)
print("Months ", month)

#writing to file
output = np.array_str(month)
file = open(".\\src\\main\\java\\com\\myprojcet\\sdaproject\\Component1\\months.txt","w")
file.write(output)
file.close()


# In[ ]:





# In[ ]:





# In[12]:





# In[14]:





# In[15]:





# In[16]:





# In[17]:





# In[18]:





# In[29]:





# In[ ]:




