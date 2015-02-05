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
 
    public void testCanGetRowWithMaximumSlice() {
        List<String> intData = (Arrays.asList("1100011","1100110","1011000","0101010","0001100"));
        List<Integer> consRowExpected = new ArrayList<Integer>(Arrays.asList(0,1,2,4));

        assertEquals(consRowExpected, new Matrix(intData).getRowsWithMaxSlice());
    }
}
