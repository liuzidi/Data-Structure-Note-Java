package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:liuzidi
 * @Description:
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 *
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 */
public class topHot100_207 {
    private List<List<Integer>> graph = new ArrayList<>();
    private int[] visited;
    private boolean valid = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int[] info : prerequisites){
            graph.get(info[1]).add(info[0]);
        }
        for(int i = 0; i < numCourses && valid; i++){
            if(visited[i] == 0) DFS(i);
        }
        return valid;
    }
    // visited = 0 未访问过， = 1 访问过，但未搜索过其相邻的所有的几点， = 2 访问过，且所有相邻节点都搜索过
    private void DFS(int i){
        visited[i] = 1;
        for(int value : graph.get(i)){
            if(visited[value] == 0){
                DFS(value);
                if(!valid){
                    return;
                }
            }else if(visited[value] == 1){
                valid = false;
                return;
            }
        }
        visited[i] = 2;
    }
}
