import java.math.BigDecimal;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
/**
 *
 * @author bla
 */
public class ArctanPi {

final BigDecimal FOUR = new BigDecimal("4");
BigDecimal arctan1_5, arctan1_239, pi;

final int roundingMode = BigDecimal.ROUND_HALF_EVEN;
final int digits;
Thread[] threads;
int numProcessors;
CyclicBarrier cb;

     public ArctanPi(int digits) {
         this.digits = digits;
         numProcessors = Runtime.getRuntime().availableProcessors();
        // numProcessors = 2;
     }



     public void startPi() {
         threads = new Thread[numProcessors];
         cb = new CyclicBarrier(numProcessors+1, new Runnable() {
             public void run() {
                 finishUp();
             }
         });

         for(int a = 0; a < numProcessors; a++) {
             threads[a] = new arctanpi.ArctanPi.MyThreads();
             threads[a].start();
         }
         try {
             cb.await();
         } catch(InterruptedException ie) {

         } catch(BrokenBarrierException be) {

         }
     }



    /**
     * Compute the value of pi to the specified number of
     * digits after the decimal point.  The value is
     * computed using Machin's formula:
     *
     *          pi/4 = 4*arctan(1/5) - arctan(1/239)
     *
     * and a power series expansion of arctan(x) to
     * sufficient precision.
     */
    public void computePi() {
        try {
        arctan1_5 = arctan(5, digits+5);
        arctan1_239 = arctan(239, digits+5);

                     cb.await();
         } catch(InterruptedException ie) {
             ie.printStackTrace();
         } catch(BrokenBarrierException be) {
             be.printStackTrace();
         }

        
    }

    
