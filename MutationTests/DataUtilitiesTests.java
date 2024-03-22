package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.api.ExpectationError;
import org.jmock.Mockery;
import org.junit.*;

public class DataUtilitiesTests {

    @Before
    public void setUp() throws Exception {
    }

    
    
 // ------------------------ A : calculateColumnTotal Tests ---------------------------------------- //
   
    // Test for CalculateColumnTotal for two rows with valid input -- Given
    @Test
    public void testA1CalculateColumnTotal() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(2));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(1, 0);
                will(returnValue(2.5));
            }
        });
        double output = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(10.0, output, .000000001d);
    }

    
    // Test for CalculateColumnTotal for two rows with negative values
    @Test
    public void testA2CalculateColumnTotalTwoNeg() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(2));
                one(values).getValue(0, 0);
                will(returnValue(-7.5));
                one(values).getValue(1, 0);
                will(returnValue(-2.5));
            }
        });
        double output = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(-10.0, output, .000000001d);
    }
    
    // Test for CalculateColumnTotal for two rows with positive and negative values
    @Test
    public void testA3CalculateColumnTotalNegPos() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(2));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(1, 0);
                will(returnValue(-2.5));
            }
        });
        double output = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(5.0, output, .000000001d);
    }
    

    // Test for CalculateColumnTotal for zero rows
    @Test
    public void testA4CalculateColumnTotalZeroRows() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(0));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(1, 0);
                will(returnValue(2.5));
            }
        });
        double output = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(0, output, .000000001d);
    }

    // Test for Calculating column total for invalid rows - INVALID INPUT
//    @Test
//    public void testA5CalculateColumnTotalNegativeRow() {
//        Mockery mockingContext = new Mockery();
//        final Values2D values = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {
//            {
//                one(values).getRowCount();
//                will(returnValue(-2));
//                one(values).getValue(0, 0);
//                will(returnValue(7.5));
//                one(values).getValue(1, 0);
//                will(returnValue(2.5));
//            }
//        });
//        double output = DataUtilities.calculateColumnTotal(values, 0);
//        assertEquals(0, output, .000000001d);
//    }

    // Test for CalculateColumnTotal for Invalid index
    @Test(expected = ExpectationError.class) //Expecting an error where it is trying to grab a column out of bounds
	public void testA6InvalidIndex() {
		Mockery mockingContext = new Mockery(); 
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 47);//try to execute on an invalid index
	     
    }
    
//   	@Test(expected = InvalidParameterException.class)
//	public void testA7CalculateColumnTotalNull(){
//	    final Values2D values = null;
//	    int num = 2;
//	    
//	    DataUtilities.calculateRowTotal(values, num);
//	}
//    
//	   	
//   	@Test
//    public void testCalculateColumnTotal() {
//        Mockery mockingContext = new Mockery();
//        final Values2D values = mockingContext.mock(Values2D.class);
//
//        // Define valid rows
//        final int[] validRows = {0, 1, 2};
//
//        mockingContext.checking(new Expectations() {
//            {
//                // Set a value for total to be greater than 0
//                one(values).getRowCount();
//                will(returnValue(3)); // Setting rowCount to 3
//                allowing(values).getValue(0, 0); // Allowing any calls to getValue
//                will(returnValue(7.5)); // Returning a value for any call to getValue
//                allowing(values).getValue(1, 0);
//                will(returnValue(2.5));
//                allowing(values).getValue(2, 0);
//                will(returnValue(5.0));
//            }
//        });
//
//        // Call the method with a value of total greater than 0
//        double output = DataUtilities.calculateColumnTotal(values, 0, validRows);
//
//        // Assert that total is set to 100 since it's initially greater than 0
//        assertEquals(100.0, output, .000000001d);
//    }
//   	
//   	

   	

// ------------------------ B : calculateRowTotal Tests ---------------------------------------- //

 // Test for calculating Total Row Values for 2 columns with valid inputs
	@Test
	public void testB1CalculateRowTotalValid() {
	    // setup
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(2));
	            one(values).getValue(0, 0);
	            will(returnValue(7.5));
	            one(values).getValue(0, 1);
	            will(returnValue(2.5));
	        }
	    });
	    double result = DataUtilities.calculateRowTotal(values, 0);
	    assertEquals(10.0, result, .000000001d);
	}


    // Test for CalculatingRowTotal for two rows with negative
    @Test
    public void testB2CalculateRowTotalTwoNegValue() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(2));
                one(values).getValue(0, 0);
                will(returnValue(-7.5));
                one(values).getValue(0, 1);
                will(returnValue(-2.5));
            }
        });
        double output = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(-10.0, output, .000000001d);
    }

    // Test for CalculatingRowTotal for two rows with negative and positive value
    @Test
    public void testB3CalculateRowTotalNegPosValue() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(2));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(0, 1);
                will(returnValue(-2.5));
            }
        });
        double output = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(5.0, output, .000000001d);
    }
    
    // Test for CalculatingRowTotal of zero columns
    @Test
    public void testB4CalculateRowTotalZeroColumn() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(0));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(0, 1);
                will(returnValue(2.5));
            }
        });
        double output = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(0, output, .000000001d);
    }

    // Test for CalculatingRowTotal with Invalid input for columns 
	@Test(expected = ExpectationError.class) //expecting an expectation error as a negative row is 
	public void testB5NegativeRow() { // not reachable
		Mockery mockingContext = new Mockery(); //create a mock of 2D value object
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(0, 1);
	             will(returnValue(2.5));
	         }
	     });
	     double result = DataUtilities.calculateRowTotal(values, -1); //test for a negative row

	}
	
//  Test for CalculatingRowTotal accessing out of Bounds index
    
	@Test(expected = ExpectationError.class) //expecting out of bounds because we are testing a column that 
	public void testB6InvalidRow() { //does not exist
		Mockery mockingContext = new Mockery(); //create a 2D value object with 2 columns 1 row
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(0, 1);
	             will(returnValue(2.5));
	         }
	     });
	     double result = DataUtilities.calculateRowTotal(values, 47); //addition for invalid row
	}
	
//	// Test for CalculatingRowTotal with null as input
//	
//   	@Test(expected = InvalidParameterException.class)
//	public void testB7CalculateRowTotalNull(){
//	    final Values2D values = null;
//	    int num = 2;
//	    
//	    DataUtilities.calculateRowTotal(values, num);
//	}
//   	
//   	@Test
//   	public void testAdjustedLoopConditionForRowTotal() {
//   	    Mockery mockingContext = new Mockery();
//   	    final Values2D values = mockingContext.mock(Values2D.class);
//
//   	    mockingContext.checking(new Expectations() {
//   	        {
//   	            one(values).getColumnCount();
//   	            will(returnValue(-2));
//   	            one(values).getValue(0, 0);
//   	            will(returnValue(7.5));
//   	            one(values).getValue(0, 1);
//   	            will(returnValue(2.5));
//   	        }
//   	    });
//   	    double output = DataUtilities.calculateRowTotal(values, 0);
//   	    assertEquals(0.0, output, .000000001d);
//   	}
   	
    @Test
    public void testCalculateRowTotal() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        // Mocking data with 3 columns
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(3));
                one(values).getValue(0, 0);
                will(returnValue(1.0));
                one(values).getValue(0, 1);
                will(returnValue(2.0));
                one(values).getValue(0, 2);
                will(returnValue(3.0));
            }
        });

        // Testing with valid columns {0, 2}
        double total = DataUtilities.calculateRowTotal(values, 0, new int[]{0, 2});
        assertEquals(4.0, total, 0.000001); // Sum of values in columns 0 and 2
    }
    
    @Test
    public void testCalculateRowTotal_SetTotalToZero() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        // Mocking data with negative column count
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(-1)); // Simulating a negative column count
            }
        });

        // Testing calculateRowTotal with validCols empty array
        double total = DataUtilities.calculateRowTotal(values, 0, new int[]{});
        assertEquals(0.0, total, 0.000001); // Expecting total to be 0.0 as colCount is less than 0
    }
    
    
 // ------------------------ C : CreateNumberArray Tests ---------------------------------------- //

 // Test for changing an Array of double data type to number data type
	@Test
	public void TestC1createNumberArrayVal(){
		double[] testPrimitiveArray = {2.4,3.4,4.6};
		Number[] testNumberArray = DataUtilities.createNumberArray(testPrimitiveArray);
		Number[] expectedNumberArray = {(Number)2.4, (Number)3.4, (Number)4.6};
		assertArrayEquals("The number array values should be the same as the input primitive array", expectedNumberArray, testNumberArray);
	}
	
	
	//Testing the createNumberArray to convert array with same length
	@Test
	public void TestC2createNumberArrayLen() {
		double[] testPrimitiveArray = {2.4,3.4,4.6};
		Number[] testNumberArray = DataUtilities.createNumberArray(testPrimitiveArray);
		assertEquals("The number array length should be the same as the input primitive array", 3, testNumberArray.length);
	}
	
	
//	//Testing if invalid object as input throws a InvalidParameterException.
//	@Test(expected = InvalidParameterException.class)
//	public void TestC3createNumberArrayNull() {
//		try {
//			DataUtilities.createNumberArray(null);
//		}catch(IllegalArgumentException e){
//		}
//	}
	
//	// Testing CreateNumberArray to convert with null values inside the data
//	@Test(expected = InvalidParameterException.class)
//	public void TestC4createNumberArrayNullValuesinArray() {
//		double[] testPrimitiveArray = new double[4];
//		DataUtilities.createNumberArray(testPrimitiveArray);
//		
//	}
	
	//Testing CreateNumberArray to change an Array of one element to Number data type
	@Test
	public void TestC5CreateNumberArrayOne() {
		double[] data = {1.0};
		Number[] Intdata = {1.0};
		
		assertArrayEquals("The Double array to Number array",
		    	  Intdata, DataUtilities.createNumberArray(data));	
	}
	
	
	
	//Testing CreateNumberArray to change an Array of one negative element to Number data type	@Test
	@Test
	public void TestC6CreateNumberArrayNegVal() {
		double[] data = {-2.4,-3.4,-4.6};
		Number[] Intdata = {-2.4,-3.4,-4.6};
		
		assertArrayEquals("The Double array to Number array",
		    	  Intdata, DataUtilities.createNumberArray(data));	
	}


// ------------------------ D : CreateNumberArray2D Tests ---------------------------------------- //

	// Testing the createNumber2DArray by first testing a correct input which is a double array2D that is populated
//	@Test
//	public void testD1createNumberArray2DVal() {
//	    double[][] testPrimitiveArray = {{2.4,3.4,4.6}, {2.7, 2, 3.4}};
//	    Number[][] testNumberArray = DataUtilities.createNumberArray2D(testPrimitiveArray);
//	    Number[][] expectedNumberArray = {{2.4,3.4,4.6}, {2.7, 2, 3.4}};
//	    assertArrayEquals("The number array values should be the same as the primitive array values", expectedNumberArray, testNumberArray);
//	}

	//Testing the createNumberArray2D to convert array with same length
	@Test
	public void testD2createNumberArray2DLen() {
	    double[][] testPrimitiveArray = {{2.4,3.4,4.6}, {2.7, 2, 3.4}};
	    Number[][] testNumberArray = DataUtilities.createNumberArray2D(testPrimitiveArray);
	    Number[][] expectedNumberArray = {{2.4,3.4,4.6}, {2.7, 2, 3.4}};
	    assertEquals("The number array length should be the same as the primitive array length", expectedNumberArray.length, testNumberArray.length);
	}

	//Testing if invalid object as input throws a InvalidParameterException.
//	@Test(expected = InvalidParameterException.class)
//	public void testD3createNumberArray2DNull() {
//	    try {
//	    	DataUtilities.createNumberArray2D(null);
//	    } catch (IllegalArgumentException e) {
//	    }
//	}

//	// Testing CreateNumberArray2D to convert with null values inside the data
//	@Test(expected = InvalidParameterException.class)
//	public void testD4createNumberArrayNullValuesinArray2D() {
//	    double[][] testPrimitiveArray = new double[4][4];
//	    try {
//	    	DataUtilities.createNumberArray2D(testPrimitiveArray);
//	    } catch (IllegalArgumentException e) {
//	    }
//	}
	
	//Testing CreateNumberArray2D to change an Array of negative elements to Number data type	@Test
//	@Test
//	public void TestD5CreateNumberArray2DNegELement() {
//		double[][] data = {{-2.4,-3.4,-4.6},{-2.7, -2, -3.4}};
//		Number[][] Intdata = {{-2.4,-3.4,-4.6},{-2.7, -2, -3.4}};
//			
//		assertArrayEquals("The Double array to Number array",
//			   Intdata, DataUtilities.createNumberArray2D(data));	    	    
//			
//		}
//	
//    @Test
//    public void testCreateNumberArray2D() {
//        double[][] testPrimitiveArray = {{2.4, 3.4, 4.6}, {2.7, 2, 3.4}};
//        Number[][] testNumberArray = DataUtilities.createNumberArray2D(testPrimitiveArray);
//
//        Number[][] expectedNumberArray = {{2.4, 3.4, 4.6}, {2.7, 2, 3.4}};
//
//        assertArrayEquals(expectedNumberArray, testNumberArray);
//    }
    
	// ------------------------ E : getCumulativePercentages Tests ---------------------------------------- //
	
	//Testing getCumulativePercentages Valid Inputs
	
//	@Test
//	public void testE1getCumulativePercentagesValid() {
//		Mockery mockingContext = new Mockery();
//		final KeyedValues inputValues = mockingContext.mock(KeyedValues.class);
//		mockingContext.checking(new Expectations() {
//			{
//				allowing(inputValues).getItemCount();
//				will(returnValue(3));
//				
//				allowing(inputValues).getKey(0);
//				will(returnValue(0));
//				
//				allowing(inputValues).getKey(1);
//				will(returnValue(1));
//				
//				allowing(inputValues).getKey(2);
//				will(returnValue(2));
//				
//				
//				//key of 0 giving value 5
//				allowing(inputValues).getValue(0);
//				will(returnValue(5.0));
//				
//				//key of 1 giving value 9
//				allowing(inputValues).getValue(1);
//				will(returnValue(9.0));
//			
//				//value of 2 giving key 2
//				allowing(inputValues).getValue(2);
//				will(returnValue(2.0));
//				
//				
//			}	
//		});
//		
//		Number expectedResult[] = {5.0/16.0, 14.0/16.0, 16.0/16.0};
//		
//		KeyedValues result = DataUtilities.getCumulativePercentages(inputValues);
//
//		Number actualResult[] = {result.getValue(0), result.getValue(1), result.getValue(2)};
//		
//		assertArrayEquals(expectedResult, actualResult);
//		
//	}
//	
//
//	
//	// Testing getCumilativePercentage for One Value in keyedValue Instance
//	@Test
//	public void testE2getCumulativePercentagesForOneVal() {
//		Mockery mockingContext = new Mockery();
//		final KeyedValues inputValues = mockingContext.mock(KeyedValues.class);
//		mockingContext.checking(new Expectations() {
//			{
//				allowing(inputValues).getItemCount();
//				will(returnValue(1));
//				
//				allowing(inputValues).getKey(0);
//				will(returnValue(0));
//							
//				
//				//key of 0 giving value 5
//				allowing(inputValues).getValue(0);
//				will(returnValue(5.0));
//				
//			}	
//		});
//		
//		Number expectedResult[] = {1.0};
//		
//		KeyedValues result = DataUtilities.getCumulativePercentages(inputValues);
//
//		Number actualResult[] = {result.getValue(0)};
//		
//		assertArrayEquals(expectedResult, actualResult);
//		
//	}
	
	// Testing getCumilativePercentage with negative values..
	
	@Test
	public void testE3getCumulativePercentagesNegVal() {
		Mockery mockingContext = new Mockery();
		final KeyedValues inputValues = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
				allowing(inputValues).getItemCount();
				will(returnValue(3));
				
				allowing(inputValues).getKey(0);
				will(returnValue(0));
				
				allowing(inputValues).getKey(1);
				will(returnValue(1));
				
				allowing(inputValues).getKey(2);
				will(returnValue(2));
				
				
				//key of 0 giving value 5
				allowing(inputValues).getValue(0);
				will(returnValue(5.0));
				
				//key of 1 giving value 9
				allowing(inputValues).getValue(1);
				will(returnValue(9.0));
			
				//value of -2 giving key 2
				allowing(inputValues).getValue(2);
				will(returnValue(-2.0));
				
				
			}	
		});
		
		Number expectedResult[] = {5.0/12.0, 14.0/12.0, 12.0/12.0};
		
		KeyedValues result = DataUtilities.getCumulativePercentages(inputValues);

		Number actualResult[] = {result.getValue(0), result.getValue(1), result.getValue(2)};
		
		assertArrayEquals(expectedResult, actualResult);
		
	}
	
	// Testing getCumilativePercentage for One negative Value

//	@Test
//	public void testE4getCumulativePercentagesOneNegVal() {
//		Mockery mockingContext = new Mockery();
//		final KeyedValues inputValues = mockingContext.mock(KeyedValues.class);
//		mockingContext.checking(new Expectations() {
//			{
//				allowing(inputValues).getItemCount();
//				will(returnValue(1));
//				
//				allowing(inputValues).getKey(0);
//				will(returnValue(0));
//							
//				
//				//key of 0 giving value -5
//				allowing(inputValues).getValue(0);
//				will(returnValue(-5.0));
//				
//			}	
//		});
//		
//		Number expectedResult[] = {1.0};
//		
//		KeyedValues result = DataUtilities.getCumulativePercentages(inputValues);
//
//		Number actualResult[] = {result.getValue(0)};
//		
//		assertArrayEquals(expectedResult, actualResult);
//		
//	}
	
//	// Testing getCumilativePercentage for exceptions.
//	@Test(expected =  InvalidParameterException.class)
//	public void testE5getCumulativePercentagesException(){
//		DataUtilities.getCumulativePercentages(null);
//	}
//	

	// ------------------------ F : Equal Tests ---------------------------------------- // 	
   	@Test
    public void testF1EqualBothNull() {
        double[][] a = null;
        double[][] b = null;
        assertTrue(DataUtilities.equal(a, b));
    }

    @Test
    public void testF2EqualFirstNull() {
        double[][] a = null;
        double[][] b = new double[][]{{1.0, 2.0}, {3.0, 4.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    public void testF3EqualSecondNull() {
        double[][] a = new double[][]{{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = null;
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    public void testF4EqualDifferentLength() {
        double[][] a = new double[][]{{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = new double[][]{{1.0, 2.0}, {3.0, 4.0}, {5.0, 6.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    public void testF5EqualDifferentValues() {
        double[][] a = new double[][]{{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = new double[][]{{1.0, 2.0}, {3.0, 5.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    public void testF6EqualSameArrays() {
        double[][] a = new double[][]{{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = new double[][]{{1.0, 2.0}, {3.0, 4.0}};
        assertTrue(DataUtilities.equal(a, b));
    }

    @Test
    public void testF7EqualEmptyArrays() {
        double[][] a = new double[][]{};
        double[][] b = new double[][]{};
        assertTrue(DataUtilities.equal(a, b));
    }
    
    
    
 // ------------------------ G : clone Tests ---------------------------------------- //

    @Test
    public void testG1CloneEmptySource() {
        double[][] source = {};
        assertArrayEquals(new double[][]{}, DataUtilities.clone(source));
    }

    @Test
    public void testG2CloneSingleRowSource() {
        double[][] source = {{1.0, 2.0, 3.0}};
        assertArrayEquals(new double[][]{{1.0, 2.0, 3.0}}, DataUtilities.clone(source));
    }

    @Test
    public void testG3CloneMultipleRowsSource() {
        double[][] source = {{1.0, 2.0, 3.0}, {4.0, 5.0}, {6.0}};
        assertArrayEquals(new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0}, {6.0}}, DataUtilities.clone(source));
    }

    @Test
    public void testG4CloneNullRowsSource() {
        double[][] source = {{1.0, 2.0, 3.0}, null, {6.0}};
        assertArrayEquals(new double[][]{{1.0, 2.0, 3.0}, null, {6.0}}, DataUtilities.clone(source));
    }

    @Test
    public void testG5CloneComplexSource() {
        double[][] source = {{1.0, 2.0, 3.0}, {4.0}, null, {}, {6.0, 7.0}};
        assertArrayEquals(new double[][]{{1.0, 2.0, 3.0}, {4.0}, null, {}, {6.0, 7.0}}, DataUtilities.clone(source));
    }
    
    
    // ------------------------ H : Test Cases Added to Increase Mutation Coverage ---------------------------------------- //
    
    @Test //
    public void testH5CalculateColumnTotalWithValidRowsSpecifiedTest() {

        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();will(returnValue(6));
                one(values).getValue(0, 0);will(returnValue(7.5));
                one(values).getValue(1, 0);will(returnValue(2.5));
                one(values).getValue(2, 0);will(returnValue(-3));
                one(values).getValue(3, 0);will(returnValue(-2));
                one(values).getValue(4, 0);will(returnValue(7));
                one(values).getValue(5, 0);will(returnValue(-3.5));
            }
        });
        int[] validrows = {0,1,2,4};
        double output = DataUtilities.calculateColumnTotal(values, 0, validrows);
        assertEquals(14.0, output, .000000001d);
    }
    
    @Test //
    public void testH6CalculateColumnTotalWithInvalidRowsTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();will(returnValue(6));
                one(values).getValue(0, 0);will(returnValue(7.5));
                one(values).getValue(1, 0);will(returnValue(2.5));
                one(values).getValue(2, 0);will(returnValue(-3));
                one(values).getValue(3, 0);will(returnValue(-2));
                one(values).getValue(4, 0);will(returnValue(7));
                one(values).getValue(5, 0);will(returnValue(-3.5));
            }
        });
        int[] validrows = {10,11,12,14};
        double output = DataUtilities.calculateColumnTotal(values, 0, validrows);
        assertEquals(0.0, output, .000000001d);
    }
    
    @Test //
    public void testH7CalculateColumnTotalWithNullValuesTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();will(returnValue(6));
                one(values).getValue(0, 0);will(returnValue(7.5));
                one(values).getValue(1, 0);will(returnValue(2.5));
                one(values).getValue(2, 0);will(returnValue(-3));
                one(values).getValue(3, 0);will(returnValue(-2));
                one(values).getValue(4, 0);will(returnValue(null));
                one(values).getValue(5, 0);will(returnValue(null));
            }

        });
        double output = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(5.0, output, .000000001d);
    }
    
    @Test //
    public void testH8calculateColumnTotalForFourValuesTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(4));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(1, 0);
                will(returnValue(2.5));
                one(values).getValue(2, 0);
                will(returnValue(-3));
                one(values).getValue(3, 0);
                will(returnValue(-2));
            }
        });
        double output = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(5.0, output, .000000001d);
    }
    
    @Test //
    public void testH9CalculateColumnTotalWithValidRowsNullValuesTest() {

        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();will(returnValue(6));
                one(values).getValue(0, 0);will(returnValue(null));
                one(values).getValue(1, 0);will(returnValue(null));
                one(values).getValue(2, 0);will(returnValue(null));
                one(values).getValue(3, 0);will(returnValue(-2));
                one(values).getValue(4, 0);will(returnValue(null));
                one(values).getValue(5, 0);will(returnValue(-3.5));
            }
        });
        int[] validrows = {0,1,2,4};
        double output = DataUtilities.calculateColumnTotal(values, 0, validrows);
        assertEquals(0.0, output, .000000001d);
    }

    @Test //
    public void testH10CloneWithAValidArrayTest() {
        double[][] expected_result = {{1,2}, {3,4}, {-8, 11}};
        double[][] actual_result = DataUtilities.clone(expected_result);
        assertArrayEquals(expected_result, actual_result);
    }

 	
 	@Test
 	public void testH11getCumulativePercentagesValid1() {
 		Mockery mockingContext = new Mockery();
 		final KeyedValues inputValues = mockingContext.mock(KeyedValues.class);
 		mockingContext.checking(new Expectations() {
 			{
 				allowing(inputValues).getItemCount();
 				will(returnValue(3));
 				
 				allowing(inputValues).getKey(0);
 				will(returnValue(0));
 				
 				allowing(inputValues).getKey(1);
 				will(returnValue(1));
 				
 				allowing(inputValues).getKey(2);
 				will(returnValue(2));
 				
 				allowing(inputValues).getValue(0);
 				will(returnValue(5.0));

 				allowing(inputValues).getValue(1);
 				will(returnValue(9.0));

 				allowing(inputValues).getValue(2);
 				will(returnValue(2.0));
 				
 			}	
 		});
 		
 		Number expected_Result[] = {5.0/16.0, 14.0/16.0, 16.0/16.0};
 		
 		KeyedValues output = DataUtilities.getCumulativePercentages(inputValues);

 		Number actual_Result[] = {output.getValue(0), output.getValue(1), output.getValue(2)};
 		
 		assertArrayEquals(expected_Result, actual_Result);
 		
 	}
 	
 	// Testing getCumilativePercentage for One Value in keyedValue Instance
 	@Test
 	public void testH12getCumulativePercentagesForOneVal1() {
 		Mockery mockingContext = new Mockery();
 		final KeyedValues inputValues = mockingContext.mock(KeyedValues.class);
 		mockingContext.checking(new Expectations() {
 			{
 				allowing(inputValues).getItemCount();
 				will(returnValue(1));
 				
 				allowing(inputValues).getKey(0);
 				will(returnValue(0));
 							
 				allowing(inputValues).getValue(0);
 				will(returnValue(5.0));
 				
 			}	
 		});
 		
 		Number expected_Result[] = {1.0};
 		
 		KeyedValues output = DataUtilities.getCumulativePercentages(inputValues);

 		Number actual_Result[] = {output.getValue(0)};
 		
 		assertArrayEquals(expected_Result, actual_Result);
 		
 	}
 	
 	// Testing getCumilativePercentage with negative values..
 	
 	@Test
 	public void testH13getCumulativePercentagesNegVal1() {
 		Mockery mockingContext = new Mockery();
 		final KeyedValues inputValues = mockingContext.mock(KeyedValues.class);
 		mockingContext.checking(new Expectations() {
 			{
 				allowing(inputValues).getItemCount();
 				will(returnValue(3));
 				
 				allowing(inputValues).getKey(0);
 				will(returnValue(0));
 				
 				allowing(inputValues).getKey(1);
 				will(returnValue(1));
 				
 				allowing(inputValues).getKey(2);
 				will(returnValue(2));
 				
 				allowing(inputValues).getValue(0);
 				will(returnValue(5.0));
 				
 				allowing(inputValues).getValue(1);
 				will(returnValue(9.0));

 				allowing(inputValues).getValue(2);
 				will(returnValue(-2.0));
 				
 				
 			}	
 		});
 		
 		Number expected_Result[] = {5.0/12.0, 14.0/12.0, 12.0/12.0};
 		
 		KeyedValues output = DataUtilities.getCumulativePercentages(inputValues);

 		Number actual_Result[] = {output.getValue(0), output.getValue(1), output.getValue(2)};
 		
 		assertArrayEquals(expected_Result, actual_Result);
 		
 	}
 	
 	// Testing getCumilativePercentage for One negative Value

 	@Test
 	public void testH14getCumulativePercentagesOneNegVal1() {
 		Mockery mockingContext = new Mockery();
 		final KeyedValues inputValues = mockingContext.mock(KeyedValues.class);
 		mockingContext.checking(new Expectations() {
 			{
 				allowing(inputValues).getItemCount();
 				will(returnValue(1));
 				
 				allowing(inputValues).getKey(0);
 				will(returnValue(0));
 							
 				
 				//key of 0 giving value -5
 				allowing(inputValues).getValue(0);
 				will(returnValue(-5.0));
 				
 			}	
 		});
 		
 		Number expected_Result[] = {1.0};
 		
 		KeyedValues output = DataUtilities.getCumulativePercentages(inputValues);

 		Number actual_Result[] = {output.getValue(0)};
 		
 		assertArrayEquals(expected_Result, actual_Result);
 		
 	}
    @After
    public void tearDown() throws Exception {
    }
    
}
	
// --------------------------- THE END ------------------------- //
