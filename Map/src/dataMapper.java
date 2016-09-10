/**
 * Created by Rafael on 3/11/2016.
 */
class Map<Key, Value> {
    private Key[] keys;
    private Value[] values;
    private int count=0;
    private int index =0;
    private static final int DEFAULT_LENGTH = 64;
    private static final int DEFAULT_INCREMENT = 8;
    //private static final int CHANGE_IN_LENGTH = 100;
    private int increment;


    public Map(int length,int increment){
        if (length < 0 || increment<=0){
            throw new IllegalArgumentException("Cant have length less than 0 or increment <= 0");
        }
        else{
            keys = (Key[])new Object[length]; // initializes my array keys
            values = (Value[])new Object[length]; // initializes my array values
            this.increment = increment;
        }
    }
    public Map(){
        this(DEFAULT_LENGTH,DEFAULT_INCREMENT);
    }

    public Value get(Key key){

        int index = where(key);
        if (index==-1){
            throw new IllegalArgumentException("key couldn't be found in the array");
        }
        else{
            return values[index];
        }

    }
    private boolean isEqual(Key leftkey, Key rightkey) {
        if (leftkey == null || rightkey == null) {
            return leftkey == rightkey;
        }
        else {
            return leftkey.equals(rightkey);
        }
    }
    public boolean isIn(Key key){
        boolean flag = false;
        for(int i=0;i<count;i++){
            if(isEqual(keys[i],key)){
                flag = true;
                break;
            }
        }
        return flag;
    }
    public void put(Key key, Value value){
        if (count>=keys.length){
            Key[] newKeys = (Key[]) new Object[keys.length + increment];
            Value[] newValues = (Value []) new Object[values.length + increment];
            for(int index=0;index<count;index++){
                newKeys[index] = keys[index];
                newValues[index] = values[index];
            }
            keys = newKeys;
            values = newValues;
        }
        keys[count] = key;
        values[count] = value;
        count = count +1;
    }

    private int where(Key key){
        boolean flag = true;
        for(int i=0;i<count;i++){
            if(isEqual(keys[i],key)){
                this.index = i;
                flag = false;
                break;
            }
        }
        if (flag){
            this.index = -1;
        }
        return this.index;
    }

}

public class dataMapper{
    public static void main(String[] args){
        Map<String,Integer> test = new Map<String, Integer>();
        String [] str_array = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        Integer [] int_array = {0,1,2,3,4,5,6,7,8,9};
        for(int i=0;i<str_array.length;i++){
            test.put(str_array[i],int_array[i]);
            System.out.println(test.get(str_array[i]));
        }
        System.out.println(test.isIn("ten"));
        test.put("ten",10);
        System.out.println(test.get("ten"));
        test.put("forty",40);
        System.out.println(test.get("forty"));
    }
}

/*
OUTPUT
0
1
2
3
4
5
6
7
8
9
false
10
40
 */