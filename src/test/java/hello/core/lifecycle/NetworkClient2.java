package hello.core.lifecycle;

/** 빈 등록 시, 초기화와 소멸 메서드라고 지정하는 방법
 * 변경 사항 : implements InitializingBean, DisposableBean 삭제 / overried 메서드 삭제
 * */

public class NetworkClient2 {
    private String url; // 접속 url

    public NetworkClient2(){
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String message){
        System.out.println("call : " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("close : " + url);
    }

    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect(); // 연결
        call("초기화 연결 메세지");
    }

    public void close() throws Exception {
        System.out.println("NetworkClient.close");
         disconnect();
    }
}
