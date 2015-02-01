package miro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class MatrixTest extends TestCase {
    
    private List<String> data;

    public void setUp() {
        data = new ArrayList<String>();
        data.add("100");
        data.add("110");
    }

    public void testCanConstructAMatrix() {
        Matrix matrix = new Matrix(data);
        
        assertEquals(data, matrix.getData());       
    }
    
    public void testCanConstructAMatrixFromAString() {
        Matrix matrix = Matrix.fromString("1,0,0\n1,1,0");
        
        assertEquals(data, matrix.getData());
    }
    public void testToRow() {
        List<String> matrixSpecification = Arrays.asList("1,0,0","1,1,0");
        List<String> rows = Arrays.asList("1,0,0\n1,1,0".split("\n"));    
        assertEquals(matrixSpecification, rows);
    }
    
    public void testToRow2() {
        String fa = ("1,0,0\n1,1,0");
        List<String> rows = Matrix.toRows(fa);
        assertEquals(Arrays.asList("1,0,0","1,1,0"), rows);
    }
    
    public void testConcatenateRows() {
        List<List<String>> stringData = new ArrayList<List<String>>();
        stringData.add(Arrays.asList("1","0","0"));
        stringData.add(Arrays.asList("1","1","0"));
        List<String> concRows = Matrix.concatenateRows(stringData);  
        assertEquals(Arrays.asList("100","110"), concRows);
    }
    
    public void testCanGetRowWithMaximumSlice() {
        List<String> intData = (Arrays.asList("1100011","1100110","1011000","0101010","0001100"));
        List<Integer> consRowExpected = new ArrayList<Integer>(Arrays.asList(0,1,2,4));
        //assertEquals(consRowExpected, Matrix.getMaxSlice(intData));
        assertEquals(consRowExpected, new Matrix(intData).getMaxSlice());
        //assertEquals(2, Matrix.fromString("0,1,1\n1,1,1\n0,0,1").getMaxSlice());
    }
}
