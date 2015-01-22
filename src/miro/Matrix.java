package miro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
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
        HashMap<Integer, Integer> maxSlicesByRow = new HashMap<Integer, Integer>();
       
        int maxInLine = 0;   
        int totalMax = 0;
        for (String row : intData) {                     
              String[] slices = row.split("0+");
              for(int x = 0; x < slices.length; x++) {
                  if (maxInLine < slices[x].length()) {
                      maxInLine = slices[x].length();
                  }
                  if (totalMax < slices[x].length()) {
                      totalMax = slices[x].length();
                  }
              }
              maxSlicesByRow.put(intData.indexOf(row), maxInLine);
              maxInLine = 0;      
        }
        
        ArrayList<Integer> onlyMaxSlicesList = new ArrayList<Integer>();
        
        for (int j = 0; j < intData.size(); j++) {
            
            if ((totalMax == (maxSlicesByRow.get(j)))) {
                onlyMaxSlicesList.add(j);
            }   
        }
		return onlyMaxSlicesList;
	}
	
	private static final class DescendingOrder implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

}
