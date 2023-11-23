/**
 * 3~9 k : 사과의 최대점수
 * 3~10 m : 한 상자에 들어가는 사과 수
 * 7~1,000,000 : 사과들의 점수
 *
 * 과일 장수가 얻을수 있는 최대이익을 return
 */





class Solution {
    fun solution(k: Int, m: Int, score: IntArray): Int {
        var answer: Int = 0
        var apple = mutableMapOf<Int,Int>()

        //크기(1~k)에 따른 사과가 몇개 있는 저장 하는 Map Apple
        for(i in k downTo 1) apple.put(i,0)
        score.forEach { apple.put(it, apple.get(it)?.plus(1)!!) }

        //값이 0인 경우를 제거
        apple.entries.removeIf{it.value == 0}
        var num = apple.values.sum()
        var box = 0
        var min = 50
        //(최저 사과 점수) x (한 상자에 담긴 사과의 개수) x (상자의 개수)
        //1. 최저의 사과 점수를 구한다.
        //2. 한 상자에 담긴 사과의 개수 = m
        //3. 상자의 개수를 구한다.
        //4. 상자로 빼고 나머지 사과의 갯수를 num으로 입력
        while(num>=m){
            for(i in apple.entries){
                box += i.value
                if(box>=m){
                    min =i.key
                    apple[min]=box-m
                    box=0 //초기화
                    break
                }
                apple[i.key]=0
            }
            answer += min*m
            num -= m
            if(apple[min]==0) apple.remove(min)
        }
        //m의 크기에 따라 계산 하여 answer 에 저장

        return answer
    }
}
fun main(){
    var a = Solution()
    a.solution(3,4, intArrayOf(1,2,3,1,2,3,1))//8
    a.solution(4,3, intArrayOf(4,1,2,2,4,4,4,4,1,2,4,2))//33
}