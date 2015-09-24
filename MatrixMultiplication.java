package hoho;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by noMoon on 2015-09-23.
 */
public class MatrixMultiplication {
    public static void main(String[] args) throws IOException {
        File file = new File("matrix.in");
        File outputFile = new File("matrix.out");
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file)));
        String firstLine=br.readLine();
        String[] matrix=firstLine.split("\\|");
        int num=matrix.length/2;
        List<List<int[]>> matrixList=new ArrayList<List<int[]>>();
        int count=0;
        int[][] index=new int[num][2];
        int temp=0;
        for(int i=0;i<num*2;i++){
            if(0==i) temp=firstLine.indexOf("|");
            else temp=firstLine.indexOf("|",temp+1);
                if(count%2==0){
                    index[count/2][0]=temp;
                }else{
                    index[count/2][1]=temp;
                }
                count++;
        }
        for(int i=0;i<num;i++){
            String str=matrix[i*2+1];
            String[] strs=str.split(" +");
            int m=strs.length-1;
            int[] row=new int[m];
            for(int j=0;j<m;j++)
                {
                    row[j]=Integer.parseInt(strs[j+1]);
                }
            List<int[]> tempMatrix=new ArrayList<int[]>();
            tempMatrix.add(row);
            matrixList.add(tempMatrix);
        }
        String context = br.readLine();
        while (null != context) {
            for(int i=0;i<num;i++){
                if(context.length()>index[i][1]){
                String rowStr=context.substring(index[i][0],index[i][1]);
                String[] strs=rowStr.substring(1).split(" +");
                if(strs.length==matrixList.get(i).get(0).length+1){
                    int m=strs.length-1;
                    int[] row=new int[m];
                    for(int j=0;j<m;j++){
                        row[j]=Integer.parseInt(strs[j+1]);
                    }
                    List<int[]> tempMatrix=matrixList.get(i);
                    tempMatrix.add(row);
                }}
            }
            context=br.readLine();
        }
        List<int[]> result=new ArrayList<int[]>();
        int n=matrixList.get(0).size();
        int m=matrixList.get(0).get(0).length;
        int o=matrixList.get(1).get(0).length;
        List<int[]> matrixOne=matrixList.get(0);
        List<int[]> matrixTwo=matrixList.get(1);
        for(int i=0;i<n;i++){
            int[] resultRow=new int[o];
            for(int j=0;j<o;j++){
                count=0;
                for(int k=0;k<m;k++){
                    count+=matrixOne.get(i)[k]*matrixTwo.get(k)[j];
                }
                resultRow[j]=count;
            }
            result.add(resultRow);
        }
        for(int l=2;l<num;l++){
            matrixOne=result;
            matrixTwo=matrixList.get(l);
            n=matrixOne.size();
            m=matrixTwo.size();
            o=matrixTwo.get(0).length;
            result=new ArrayList<int[]>();
            for(int i=0;i<n;i++){
                int[] resultRow=new int[o];
                for(int j=0;j<o;j++){
                    count=0;
                    for(int k=0;k<m;k++){
                        count+=matrixOne.get(i)[k]*matrixTwo.get(k)[j];
                    }
                    resultRow[j]=count;
                }
                result.add(resultRow);
            }
        }
        n=result.size();
        m=result.get(0).length;
        String[][] output=new String[n][m];
        int[] lengths=new int[m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                output[i][j]=String.valueOf(result.get(i)[j]);
                if(output[i][j].length()>lengths[j]){
                    lengths[j]=output[i][j].length();
                }
            }
        }
        for(int i=0;i<n;i++){
            String str="| ";
            for(int j=0;j<m;j++){
                if(output[i][j].length()<lengths[j]){
                    for(int k=0;k<lengths[j]-output[i][j].length();k++){
                        str+=" ";
                    }
                }
                str+=output[i][j];
                str+=" ";
            }
            str+="|";
            bw.write(str);
            bw.newLine();
        }

        bw.close();
        br.close();
    }
}
