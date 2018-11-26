package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MinHash{

    private int numHash; //Número de hashfunctions que vão ser usados
    private int[] hash; //Utilizado para guardar valores aleatórios que vão ser xored com as strings

    public MinHash(int numHash){
        this.numHash = numHash;
        hash = new int[numHash];
        for(int i = 0; i < numHash; i++){
            hash[i] = Math.abs((int)(Math.random()*Integer.MAX_VALUE));
        }
    }

    /*
     * Calcula a similaridade de Jaccard de 2 sets
     * set1 e set2 são dois sets de shingles
     */ 
    public double jaccardSimilarity(Set<String> set1, Set<String> set2){
        Set<Integer> hashSet1 = new TreeSet<Integer>(convertIntArrayToList(getStringHashSet(set1)));
        Set<Integer> hashSet2 = new TreeSet<Integer>(convertIntArrayToList(getStringHashSet(set2)));

        Set<Integer> intersept = new TreeSet<>();
        intersept.addAll(hashSet1);
        intersept.retainAll(hashSet2);

        Set<Integer> union = new TreeSet<>();
        union.addAll(hashSet1);
        union.addAll(hashSet2);

        return (double)intersept.size()/(double)union.size();
    }


    //Utilizado para formatar os strings para aumentar a chance de um output correcto
    public static String formatString(String str){
        return str.toLowerCase().trim()
									                                     .replaceAll(" +", "")
									                                     .replaceAll("\t", "")
									                                     .replaceAll("\n","")
									                                     .replaceAll(" ", "");
    }

    private static List<Integer> convertIntArrayToList(int[] intArray){
        List<Integer> newList = new ArrayList<>();
        for(int i : intArray){
            newList.add(new Integer(i));
        }

        return newList;
    }

    //Devolve o hash set do string com os valores minimos dos hashes
    private int[] getStringHashSet(Set<String> shingles){

        int[] hashValues = new int[shingles.size()];
        Iterator<String> setIterator = shingles.iterator();

        for(int i = o; i < hashValues.length; i++){
            hashValues[i] = getMinHashValue(setIterator.next());
        }

        return hashValues;
    }

    /*Devolve o valor minimo de valores hash para o shingle
     *Aplica hashfunctions 'numhash'
     */
    private int getMinHashValue(String shingle){

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < numHash; i++){
            int hashCode = shingle.hashCode() ^ hash[i];
            min = Math.min(min, hashCode);
        }

        return min;
    }
}