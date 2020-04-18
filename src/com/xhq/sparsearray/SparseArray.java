package com.xhq.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        int[][] arr = new int[11][11];
        arr[2][3] = 1;
        arr[3][4] = 2;

        //输出原始的二维数组
        System.out.println("原始的二维数组如下：");
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }

        //将二维数组转换成稀疏数组
        //1.先遍历二维数组，得到非0元素的个数
        int sum = 0;
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                if(arr[i][j] != 0){
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存到sparseArr中
        int count = 1;
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                if(arr[i][j] != 0){
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                    count++;
                }
            }
        }

        //输出稀疏数组
        System.out.println("得到的稀疏数组为：");
        for(int i = 0; i < sum + 1; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(sparseArr[i][j] + "\t");
            }
            System.out.println();
        }

        //将稀疏数组恢复成原始的二维数组
        //1.先读取稀疏数组的第一行，根据第一行的数据创建原始数组
        int[][] oldArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.再读取稀疏数组后几行的数据，赋值给原始数组
        for(int i = 1; i < sum + 1; i++){
            oldArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组如下：");
        for(int[] row : oldArr){
            for(int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }


}
