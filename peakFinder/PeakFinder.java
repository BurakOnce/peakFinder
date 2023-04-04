/* 
Burak Once  @burakonce9     20120205052
*/
import java.util.Random;

public class PeakFinder {

    /**
     * generate random 2D array
     */ 
    
    public int[][] random2dArray(int n, int m){
        int[][] a1 = new int[n][];
        Random ran = new Random();
        for(int i = 0; i < n; i++){
            a1[i] = new int[m];
            for (int j = 0; j < a1[i].length; j++){
                a1[i][j] = ran.nextInt(20);
            }
        }
        return a1;
        

    }

    public int[][][] random3dArray(int r, int c, int d)
    {
        int[][][] a2 = new int[r][c][d]; //NullPointer X
        Random ran = new Random(); 
        for(int i = 0; i < r; i++) // 3 boyutlu array'in ilk indexi için
        {
            for(int y = 0; y < c; y++) // 3 boyutlu array'in ikinci indexi için
            {
                for (int j = 0; j < a2[i][y].length; j++) //// 3 boyutlu array'in üçüncü indexi için
                {
                    a2[i][y][j] = ran.nextInt(20);
                }
            }
        }
        return a2;
    }

    public void print2d(int[][] a1){
        for (int[] i: a1){
            for (int item: i){
                System.out.print(item+"\t ");
            }
            System.out.println("");        
        }
    }

    public void print3d(int[][][] a2 , int r, int c, int d)
    {
        for(int i = 0; i < r; i++)
        {
            for(int y = 0; y < c; y++)
            {
                for (int j = 0; j < a2[i][y].length; j++)
                {
                    System.out.println(a2[i][y][j]);     
                }
                System.out.println("");
            }
            System.out.println("");
        }
    }

   
        public int[] peakFinder2D(int[][] a, int n, int m, int rowOrcol)   {
        int[] indx = new int[2];
        int startR = 0, endR = n, startC = 0, endC = m; 
        int mid;
       if( rowOrcol==0 || (rowOrcol==2 && n>=m) ){  //hem roworcol için hem de satır ve sütun sayısının farklılığı için
           while(startR >= 0 && endR <= n ){ //satır sayısı kontrol
             mid = (startR+endR)/2; //orta kısımdan başlanır
             int max = a[mid][startC]; //kıyas için seçilen max
             int imax = 0;
                for(int i = startC; i < endC; i++){ //sütun peak i için 
                 if(max<a[mid][i]){
                    max = a[mid][i];
                    imax = i; }} 
                if(a[mid][imax] < a[mid+1][imax]) {//kıyaslanacak bölge belirlenmesi
                    startR = mid+1;     }
                else if(a[mid][imax] < a[mid-1][imax]) {//kıyaslanacak bölge belirlenmesi
                endR = mid-1; }
                else {// iki ife girmediyse peak orasıdır
                    indx[0] = mid;
                    indx[1] = imax;
                return indx; }  }  }

        else if(rowOrcol==1 || (rowOrcol==2 && m>n) ) { //öncekinin satır değil de sütun bölen hali
            while(startC >= 0 && endC <= m ){
                mid = (startC+endC)/2;
                int max = a[startR][mid];
                int imax = 0;
                for(int i = startR; i < endR; i++)   {
                    if(max<a[i][mid]){
                        max = a[i][mid];
                        imax = i; } } 
                if(a[imax][mid] < a[imax][mid+1]){
                    startC = mid+1; }
                else if(a[imax][mid] < a[imax][mid-1]) {
                    endC = mid-1; }
                else{
                    indx[0] = imax;
                    indx[1] = mid;
                    return indx;}   }   }
        return indx;
    }




    /**
     * uc boyutlu arrayde peak bulan method
     * @param a
     * @param r
     * @param c
     * @param d
     * @return
     */
    public int[] peakFinder3d(int[][][] a, int r, int c, int d){
        int[] indx = new int[2];
        int startR = 0, endR = r, startC = 0, endC = c , endD = d , startD = 0;
        int mid;

        while(startD >= 0 && endD <= r ){
            mid = (startD+endD)/2;
            int max = a[startR][startC][mid];
            int imax = 0 ,jmax = 0,dmax = 0 ;
            for(int i = startR; i < endR; i++){
        for(int j = startC; j < endC; j++){

                if(max<a[i][j][mid]){
                    max = a[i][j][mid];
                    imax = i;
                    jmax = j;
                }
            }
        }
           
            if(a[imax][jmax][mid] < a[imax][jmax][mid+1]){
                startD = mid+1;
            }else if(a[imax][jmax][mid] < a[imax][jmax][mid-1]){
                startD = mid-1;
            }else {
        indx[0] = imax;
        indx[1] = jmax;        
        indx[2] = mid;
        return indx;
            }

            for(int i = startD; i < endD; i++){
                if(max<a[imax][jmax][i]){
                    max = a[imax][jmax][i];
                    dmax = i;
                    indx[2] = dmax;
                }

        }

       
    }
    return indx;
}

    
    public static void main(String[] args){

        PeakFinder pf = new PeakFinder();
        int m = 10, n = 10, rowOrcol =2;
        //int r = 10, c = 10, d = 10;
        
        int[][] a1 = pf.random2dArray(n,m);
        //int[][][] a2 = pf.random3dArray(r,c,d);
        
        long t1 = System.nanoTime();
       
        int[] x = pf.peakFinder2D(a1, n, m, rowOrcol);
        System.out.println(x[0]+" "+x[1]);
        //pf.print2d(a1);

        //int[] y = pf.peakFinder3d(a2, r, c, d);
        //System.out.println(y[0]+" "+y[1]+" "+y[2]);
        //pf.print3d(a2 , 10 , 10 , 10);
        

        long t2 = System.nanoTime();
        
        System.out.printf("%d, %d, %d, %d", n,m, rowOrcol, t2-t1);
       
     


    }
}