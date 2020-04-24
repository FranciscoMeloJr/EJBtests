#!/bin/bash
START=0
END=100
echo "Countdown"

for (( c=$START; c<=$END; c= $c + 5))
do
	grep -Ril "real="$0.$c
	echo "0."$c
done
 
for (( c=$START; c<=$END; c= $c + 5))
do
	grep -Ril "real="$c
	echo $c
done
START=0