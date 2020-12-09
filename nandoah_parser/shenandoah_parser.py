# abbrev_example.py
import argparse
import re

#Read the file
def read_file_(file_name, display=False):
	print("Opening file")
	print(file_name)
	try:
		with open (args.file, "r") as myfile:
			data = myfile.read().splitlines()
			if display:
				print(data)
		return data
	except:
	  print("File not found") 

#show JDK version:
def jdk_version(data):
	print(data[1])

#break data in tokers
def break_data(data, display=False):
	first_split = []
	for i in data:
		first_split.append(i.split())

	if display:
		print(first_split)

	return first_split

#show heap flags:
def heap_info(data):
	substring = "heap"
	tokens = break_data(data)
	i = 0
	for each_token in tokens:
		print(each_token)

#Main:
my_parser = argparse.ArgumentParser()
my_parser.add_argument('-f','--file', help="File to be read", required=True)
my_parser.add_argument('-v', "--version", help = "Output JVM version", action='store_true')
my_parser.add_argument('-he', "--heap", help = "Output Heap Info", action='store_true')

args = my_parser.parse_args()

#
data = read_file_(args.file)
if args.version:
	jdk_version(data)

if args.heap:
	heap_info(data)
