package hoho;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by noMoon on 2015-09-23.
 */
public class PickYourProblems {

    public static class Problem{
       public int id;
        public int score;
        public int time;
        public double value;
        Problem(int a,int b,int c,double d){
            id=a;
            score=b;
            time=c;
            value=d;
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("Pick your problems.in");
        File outputFile = new File("Pick your problems.out");
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file)));
        String firstLine=br.readLine();
        int totalTime=Integer.parseInt(firstLine);
        String context = br.readLine();
        List<Problem> pList=new ArrayList<Problem>();
        while (null != context) {
            String[] strs=context.split(",");
            int id=Integer.valueOf(strs[0]);
            int score=Integer.valueOf(strs[1]);
            int t=Integer.valueOf(strs[2]);
            double ttt=(double)score/(double)t;
            Problem p=new Problem(id,score,t,ttt);
            pList.add(p);
            context=br.readLine();
        }
        Collections.sort(pList, new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                if (o1.value > o2.value) {
                    return -1;
                } else if (o1.value < o2.value) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        int time=totalTime;
        double score=0;
        int i=0;
        int[][] result=new int[pList.size()][2];

        while(time>0&&i<pList.size()){
            Problem p=pList.get(i++);
            if(p.time<=time){
                time-=p.time;
                score+=p.score;
                result[p.id-1][0]=1;
                result[p.id-1][1]=0;
            }else{
                score+=p.score*time/p.time;
                result[p.id-1][0]=1;
                result[p.id-1][1]=1;
                time=0;
            }
        }
        bw.write(String.valueOf((int)score));
        bw.newLine();
        String str="";
        for(i=0;i<pList.size();i++){
            if(result[i][0]>0){
                str+=String.valueOf(i+1);
                if(result[i][1]>0){
                    str+="*";
                }
                str+=",";
            }
        }
        bw.write(str.substring(0,str.length()-1));
        bw.close();
        br.close();
    }
}
