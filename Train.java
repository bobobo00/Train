import java.util.*;

/**
 * @ClassName Train
 * @Description TODO
 * @Auther danni
 * @Date 2019/12/2 19:45]
 * @Version 1.0
 **/

public class Train {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
       // while(in.hasNext()){
            int n=3;//in.nextInt();
            //输入火车编号
            int[] A=new int[n];
            for (int i = 0; i <n ; i++) {
                A[i]=i+1;//in.nextInt();
            }
            int start=0;
            //计算n个火车的出栈的编号的排列组合
            ArrayList<int[]> result=new ArrayList<>();
            permutation(A,start,n,result);
            //出栈的结果，一个元素一个记录
            Set<String> sortResult=new TreeSet<>();
            //循环排列组合
            for(int[] out:result){
                if(isLegal(A,out,n)){
                    //满足的组合，输入结果，每一个编号用空格分隔
                    StringBuilder sb=new StringBuilder();
                    for(int i=0;i<n;i++){
                        sb.append(out[i]+" ");
                    }
                    sb.append(out[n-1]);
                    sortResult.add(sb.toString());
                }
            }
            //最后输出所有的符合出栈要求的组合
            for(String list:sortResult){
                System.out.println(list);
            }
      //  }
       // in.close();
    }
    /*
       in:火车编号数组
       out：火车出栈顺序
       n：火车数量
     */
    private static boolean isLegal(int[] in, int[] out, int n) {
        //栈存储进站的火车编号
        LinkedList<Integer> stack=new LinkedList<>();
        int i=0;
        int j=0;
        while(i<n){//in还有元素的时候都需要判断
            if(in[i]==out[i]){
                i++;
                j++;
            }else{
                if(stack.isEmpty()){//空stack就只有入栈了
                    stack.push(in[i]);
                    i++;
                }else{
                    int top=stack.peek();//栈顶元素相等，进行出栈
                    if(top==out[j]){
                        j++;
                        stack.pop();
                    }else if(i<n){//不相等时候入栈，说明还有待进站的车
                        stack.push(in[i]);
                        i++;

                    }
                }
            }
        }
        while(!stack.isEmpty()&&j<n){//in的结束后，栈中元素进程出栈序列应该和out剩余的元素相同
            int top=stack.pop();
            if(top==out[j]){
                j++;
            }else{
                return false;
            }
        }
        return true;
    }

    /* *
        * @Author danni
        * @Description 求出所有排列
        * @Date 19:52 2019/12/2
        * @Param [a, start, n, result]
        * @return void
     **/
    private static void permutation(int[] A, int start, int n, ArrayList<int[]> result) {
        if(start==n){
            return;
        }
        if(start==n-1){
            int[] B=A.clone();
            result.add(B);
        }
        for(int i=start;i<n;i++){
            swap(A,start,i);
            permutation(A,start+1,n,result);
            swap(A,start,i);
        }
    }

    private static void swap(int[] A, int j, int i) {
        int t=A[i];
        A[i]=A[j];
        A[j]=t;

    }
}
