package miro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {

	private List<String> data;

	public Matrix(List<String> data) {
		this.data = data;
	}

	public List<String> getData() {
		return data;
	}

	public static Matrix fromString(String matrixSpecification) {
	    List<String> rows = toRows(matrixSpecification);
		List<List<String>> stringData = toStringMatrix(rows);
		List<String> intData = concatenateRows(stringData);

		return new Matrix(intData); 
	}
	
	static List<String> toRows(String matrixSpecification) {
        List<String> rows = Arrays.asList(matrixSpecification.split("\n"));     
        return rows;
    }
	
	private static List<List<String>> toStringMatrix(List<String> rows) {  
        List<List<String>> stringData = new ArrayList<List<String>>();

        for(String row : rows) {
            stringData.add(Arrays.asList(row.split(",")));
        }
        return stringData;
    }
	
	static List<String> concatenateRows(List<List<String>> stringData) {
		ArrayList<String> intData = new ArrayList<String>();
		
		for(List<String> row : stringData) {
			String integerRow = "";
			
			for(String value : row) {
				integerRow += value;
			}
			
			intData.add(integerRow);
		}
		return intData;
	}
	
	
	public static List<Integer> getMaxSlice(List<String> intData) {
	    ArrayList<Integer> onlyMaxSlicesList = new ArrayList<Integer>();
	    ArrayList<Integer> maxSliceForEveryRowList = new ArrayList<Integer>();
        String[] intDataStrArr = intData.toArray(new String[intData.size()]);
        
	    char c = '1';
        int counterInLine = 0;
        int maxInLine = 0;          
        for (int z = 0; z < intDataStrArr.length; z++) {                     
              for (int i = 0; i < intDataStrArr[z].length(); i++) {
                if (intDataStrArr[z].charAt(i) == c)  { 
                    counterInLine++;
                    if (counterInLine > maxInLine) {    
                        maxInLine = counterInLine;
                    }
                }            
                    else if (counterInLine > 0) {  
                        if (counterInLine > maxInLine) {
                            maxInLine = counterInLine;              
                        }
                        counterInLine = 0;                    
                    }       
              }             
              maxSliceForEveryRowList.add(z, maxInLine);              
              maxInLine = 0;
              counterInLine = 0;      
        }  
        int maxSlice = maxSliceForEveryRowList.get(0); 
        int maxSliceIndex = 0;
        
        for (int i = 0; i < maxSliceForEveryRowList.size(); i++) {
            if ((maxSliceForEveryRowList.get(i) >=  maxSlice) && (maxSliceForEveryRowList.get(i) != 0)) {   
                maxSlice = (maxSliceForEveryRowList.get(i));
                maxSliceIndex = i;
            }   
        } 
        
        for (int j = 0; j < maxSliceForEveryRowList.size(); j++) {
            
            if ((maxSliceForEveryRowList.get(maxSliceIndex) == (maxSliceForEveryRowList.get(j))) && ((maxSliceForEveryRowList.get(j) != 0))) {
            onlyMaxSlicesList.add(j);
            }   
        }
		return onlyMaxSlicesList;
	}

}
