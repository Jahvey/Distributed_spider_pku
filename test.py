
#encoding=utf-8
import sys
import urllib
import json
import webbrowser
import os
import collections
import sys
"""
class testDemo(object):
	def __init__(self,sex):
		self.name="momo"
		self.sex=sex
	def momo(self,sex):
		print self.name,sex

a=testDemo('man')
a.momo('woman')

def testinput():
	a=sys.argv[1]
	b=sys.argv[2]
	print a+' '+b

testinput()
"""
def testurl():
	url="http://www.baidu.com"
	response=urllib.urlopen(url).read()
	#pjspn=json.loads(response)
	a=response.find("text")
	print a
testurl()
