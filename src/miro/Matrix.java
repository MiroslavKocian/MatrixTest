package miro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Matrix {
    
    private List<String> data;
    private Map<Integer, Integer> maxSlicesByRow;
    private Integer totalMaxSliceSize;

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
	

	public List<Integer> getMaxSlice() {
        ArrayList<Integer> rowsWithMaxSlice = new ArrayList<Integer>();
        for (Entry<Integer, Integer> rowEntry : getMaxSlicesByRow().entrySet()) {           
            if (rowEntry.getValue() == getTotalMaxSliceSize()) {
                rowsWithMaxSlice.add(rowEntry.getKey());
            }   
        }
        return rowsWithMaxSlice;
    }

	private int getTotalMaxSliceSize() {
	    if (totalMaxSliceSize == null) totalMaxSliceSize = computeTotalMaxSliceSize();
        return totalMaxSliceSize;
	}
	
    private int computeTotalMaxSliceSize() {
        ArrayList<Integer> maxSlices = new ArrayList<Integer>(getMaxSlicesByRow().values());
        maxSlices.sort(new DescendingOrder());
        
        int totalMax = maxSlices.get(0);
        return totalMax;
    }
	
	private Map<Integer, Integer> getMaxSlicesByRow() {
	    if (maxSlicesByRow == null) maxSlicesByRow = computeMaxSlicesByRow();
	    return maxSlicesByRow;
	}

    private Map<Integer,Integer> computeMaxSlicesByRow() {
        HashMap<Integer, Integer> maxSlicesByRow = new HashMap<Integer, Integer>();
        for (String row : data) {                     
              List<String> slices = Arrays.asList(row.split("0+"));
              slices.sort(new ByLength());
              maxSlicesByRow.put(data.indexOf(row), slices.get(0).length());      
        }
        return maxSlicesByRow;
    }
	
	private static final class ByLength implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
    }

    private static final class DescendingOrder implements Comparator<Integer> {
        @Override
        public int compare(Integer arg0, Integer arg1) {
            return arg1.compareTo(arg0);
        }
    }

}
