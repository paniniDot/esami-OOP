## Preparazione per Esame OOP 
Esami di **OOP** fatti da panini degli anni passati 
### Come eseguire da linea di comando
1. Compilazione
``` 
javac -d /bin -cp /src /src/packages/nomeFileMain.java
```
- *-d* indica al compilatore quale sarà la directory nella quale dovrà inserire il byte-code.
N.B.: la struttura interna della directory /bin rifletterà la struttura dei package. 
- *-cp* specifica la directory di partenza dalla quale il compilatore dovrà cercare i sorgenti da cui la classe main è dipendente

2. Linking ed esecuzione
```
java -cp /bin packages.NomeFileMain 
```
- *-cp* questa volta indica la directory di partenza dalla quale il linker troverà il byte-code dei sorgenti che linkerà insieme, 
- il *nome del file da eseguire* dovrà essere **pienamente qualitifcato** ovvero corrispondere al nome della classe main (senza estensione .java) preceduta dai package dentro i quali è definita