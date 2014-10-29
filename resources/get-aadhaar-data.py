import datetime
import os
import urllib

today = datetime.date.today()
#today = datetime.date(2013,8,4)
print "you will have all data files from:"+str(today)

#print onemonth
#dateonemonthago=today-onemonth
#print dateonemonthago

datasetcode="UIDAI-ENR-DETAIL"
''' Keep on changing the datasetcode name here to download all data as mentioned in https://data.uidai.gov.in/uiddatacatalog/dataCatalogHome.do''' 

filename="UIDAI-dataset" 

'''The last dataset downloaded is from 2012-01-01 till present ''' 

while(today > datetime.date(2012,1,1)):
   # print today
    onemonth = datetime.timedelta(days=29)
    onemonthago = today - onemonth
    RestUrl="https://data.uidai.gov.in/uiddatacatalog/rest/"+datasetcode+"/"+str(onemonthago.strftime('%Y%m%d'))+"/"+str(today.strftime('%Y%m%d'))
    print RestUrl
    #fh=open(filename,"w")
    response = urllib.urlretrieve(RestUrl,datasetcode+str(onemonthago.strftime('%Y%m%d'))+"-"+str(today.strftime('%Y%m%d'))+".zip")
    today = onemonthago 
    
print "Files downloaded will be available as .zip"

