package programmers.pccp.park.svkim;

import java.util.Arrays;

public class Solution {
    public int solution(int[] mats, String[][] park) {
        Arrays.sort(mats);

        for(int m=mats.length-1; m >= 0; m--) {
            int mat = mats[m];
            for(int i = 0; i <= park.length - mat; i++) {
                for(int j = 0; j <= park[0].length - mat; j++) {
                    if(park[i][j].equals("-1")) {
                        if(enablePicnic(mat, new int[]{i,j}, park)) {
                            return mat;
                        }
                    }
                }
            }
        }

        return -1;
    }

    public boolean enablePicnic(int mat, int[] start, String[][] park) {
        if(start[0] + mat > park.length || start[1] + mat > park[0].length) return false;

        for(int i=start[0]; i < start[0]+mat; i++) {
            for(int j=start[1]; j < start[1]+mat; j++) {
                if(!park[i][j].equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }
}
