package coe528.project;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Aaron
 */
public class EnvironmentObjectTest  {
    
    public EnvironmentObjectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   /**
     * Test of EnvironmentObject constructor following boundary

     */
    @Test
    public void testBoundary() {
        
        EnvironmentObject test = new EnvironmentObjectImpl(0,10,10);
        //assertEquals(0, test.imageIndex);

    }
 
    /**
     * Test of EnvironmentObject constructor with incorrect boundaries
     * Black box 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testInvalidBoundary() {
    EnvironmentObject test = new EnvironmentObjectImpl(-10,-5,-5);
    }
   
    /**
     * Test of EnvironmentObject constructor with incorrect image boundary
     * Black box 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testInvalidBoundary1() {
    EnvironmentObject test = new EnvironmentObjectImpl(-5,10,10);
    }
    
    /**
     * Test of EnvironmentObject constructor with incorrect boundary
     * Black box 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testInvalidBoundary2() {
        EnvironmentObject test = new EnvironmentObjectImpl(50,-100,-100);
    }

    public class EnvironmentObjectImpl extends EnvironmentObject {

        public EnvironmentObjectImpl(int image, int width, int height) {
            
            super(image, width, height);
        }
        

        @Override
        public void update(IObserverSubject ios) {
        }
    }
    
}
