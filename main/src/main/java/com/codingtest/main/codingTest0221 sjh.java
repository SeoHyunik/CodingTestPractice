package com.codingtest.main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 신입사원 무지는 게시판 불량 이용자를 신고하고 처리 결과를 메일로 발송하는 시스템을 개발하려 합니다. 무지가 개발하려는 시스템은 다음과 같습니다.

각 유저는 한 번에 한 명의 유저를 신고할 수 있습니다.
신고 횟수에 제한은 없습니다. 서로 다른 유저를 계속해서 신고할 수 있습니다.
한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.
k번 이상 신고된 유저는 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송합니다.
유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면서 정지 메일을 발송합니다.
다음은 전체 유저 목록이 ["muzi", "frodo", "apeach", "neo"]이고, k = 2(즉, 2번 이상 신고당하면 이용 정지)인 경우의 예시입니다.

유저 ID	       유저가 신고한 ID 	설명
"muzi"	       "frodo"	           "muzi"가 "frodo"를 신고했습니다.
"apeach"	   "frodo"	           "apeach"가 "frodo"를 신고했습니다.
"frodo"	       "neo"	           "frodo"가 "neo"를 신고했습니다.
"muzi"         "neo"	           "muzi"가 "neo"를 신고했습니다.
"apeach"	   "muzi"	           "apeach"가 "muzi"를 신고했습니다.
각 유저별로 신고당한 횟수는 다음과 같습니다.

유저 ID	신고당한 횟수
"muzi"	1
"frodo"	2
"apeach"	0
"neo"	2
위 예시에서는 2번 이상 신고당한 "frodo"와 "neo"의 게시판 이용이 정지됩니다. 이때, 각 유저별로 신고한 아이디와 정지된 아이디를 정리하면 다음과 같습니다.

유저 ID	       유저가 신고한 ID	     정지된 ID
"muzi"	      ["frodo", "neo"]	    ["frodo", "neo"]
"frodo"	      ["neo"]	            ["neo"]
"apeach"	  ["muzi", "frodo"]	    ["frodo"]
"neo"	      없음	                없음
따라서 "muzi"는 처리 결과 메일을 2회, "frodo"와 "apeach"는 각각 처리 결과 메일을 1회 받게 됩니다.

이용자의 ID가 담긴 문자열 배열 id_list, 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 report, 정지 기준이 되는 신고 횟수 k가 매개변수로 주어질 때, 
각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 하도록 solution 함수를 완성해주세요.

제한사항
2 ≤ id_list의 길이 ≤ 1,000
1 ≤ id_list의 원소 길이 ≤ 10
id_list의 원소는 이용자의 id를 나타내는 문자열이며 알파벳 소문자로만 이루어져 있습니다.
id_list에는 같은 아이디가 중복해서 들어있지 않습니다.
1 ≤ report의 길이 ≤ 200,000
3 ≤ report의 원소 길이 ≤ 21
report의 원소는 "이용자id 신고한id"형태의 문자열입니다.
예를 들어 "muzi frodo"의 경우 "muzi"가 "frodo"를 신고했다는 의미입니다.
id는 알파벳 소문자로만 이루어져 있습니다.
이용자id와 신고한id는 공백(스페이스)하나로 구분되어 있습니다.
자기 자신을 신고하는 경우는 없습니다.
1 ≤ k ≤ 200, k는 자연수입니다.
return 하는 배열은 id_list에 담긴 id 순서대로 각 유저가 받은 결과 메일 수를 담으면 됩니다. */
class Solution3 {

/*  이용자 id : id_list
    피신고자 id : report
    정지 기준 : k */
    
    public static void main(String[] args){
        String[] id_list = {"muji", "frodo", "seo", "hyunik"};
        String[] report = {"muji hyunik", "muji seo", "muji frodo", "frodo muji", "frodo seo", "frodo hyunik", "seo muji", "seo frodo", "seo hyunik", 
                            "hyunik frodo", "hyunik frodo", "hyunik frodo", "muji frodo"};
        int k = 2;
        System.out.println(solution(id_list, report, k));
    }

    public static ArrayList<Long> solution(String[] id_list, String[] report, int k) {
        ArrayList<Long> answer = new ArrayList<>();
        
        // 1. id_list에서 id를 꺼내 map으로 변형하여 우선 val값 0으로 설정한다.
        Map<String, Long> resultMap = new HashMap();
        for(String id : id_list){
            resultMap.put(id, 0L);
        }
        
        Map<String, ArrayList<String>> reportMap = new HashMap();
        for(String data : report){
            // 2. report를 반복하여 data를 꺼내 " "를 기준으로 분리하여 배열로 만들어 준다.
            String[] splitedDataArr = data.split(" ");
            
            // 3. reportMap에 피신고자의 이름으로 된 key 값이 있을 때
            if(reportMap.containsKey(splitedDataArr[1])){
                // 피신고자의 이름으로 된 key값으로 찾은 reportMap의 value값이 신고자의 이름과 중복되지 않는다면,
                if(!reportMap.get(splitedDataArr[1]).contains(splitedDataArr[0])){
                    // 해당 피신고자 이름의 map override하여 reporterArr를 업데이트 한다.
                    reportMap.get(splitedDataArr[1]).add(splitedDataArr[0]);
                }
            }else{ // reportMap에 피신고자의 이름으로 된 key 값이 없다면
                ArrayList<String> reporterArr = new ArrayList<>();
                // reporter명이 담긴 배열에 신고자의 이름을 넣어주고,
                reporterArr.add(splitedDataArr[0]);
                // reportMap에 피신고자의 이름으로 key를 넣고 신고자 이름이 담긴 배열을 넣어준다. 
                reportMap.put(splitedDataArr[1], reporterArr);
            }//end else
        }//end for

        // 4. key만으로 for문을 돌려 딸려나온 reporterArr의 길이(신고자 수)를 k값과 비교한다.
        for(String key : reportMap.keySet()){
            if(reportMap.get(key).size()>=k){
                // k보다 크다면 메세지를 보내야 하기 때문에 id들을 뽑아와
                for(String id : reportMap.get(key)){
                    // 초기에 만들어 둔 빈값의 resultMap에 id값에 맞춰 +1을 해준다. ==> 반복문이 돌면서 신고한 만큼 추가됨.
                    resultMap.put(id, resultMap.get(id)+1);
                }
            }//end if
        }//end for

        // 5. for문을 돌려 id를 key로 검색하여 담겨있는 신고 응답 건수를 뽑아 답안 배열에 넣는다.
        for(String id : id_list){
            answer.add(resultMap.get(id));
        }

        return answer;
    }
}