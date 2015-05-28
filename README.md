####Prob 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
1. Tomcat은 `Servlet Container`의 일종입니다. 
- `web.xml`을 읽어서 설정파일을 적용합니다.
- `servlet Context` 객체를 만듭니다.
- 등록된 모든 servlet들을 초기화시킵니다.
- 초기화 시, `@WebListener`로 등록된 `ContextLoaderListener`에게 초기화가 되었음이 알려지고, 그 때 `contextInitialized()` method가 실행됩니다.
- load_on_startup 설정에 따라서 `DispatcherServlet`의 `init()` method가 실행됩니다.

> 0이면 초반에 한꺼번에 실행되고, 0보다 큰 수이면 요청이 올 때 실행됩니다. 우리 서비스에서는 1이기 때문에 요청이 올 때 실행됩니다.
- `Servlet Container`에 client의 요청이 오면, `service()` method가 실행됩니다.
- `service()` method를 통해 각 요청에 대한 thread가 만들어지고, 요청한 client의 request방식에 따라 `do-() method`를 실행합니다.

####Prob 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
1. `localhost:8080`으로 접근할 경우, index.jsp로 연결됩니다.

> 초기에 연결될 파일을 지정할 수 있지만, 지정되어 있지 않은 경우 index.jsp로 연결됩니다. 
- `index.jsp`에 `/list.next`로 redirect가 걸려있는 것이 확인됩니다.
	이 경우 `DispatcherServlet`에서 `init()` method가 실행되고, `RequestMapping`에서 `initMapping`으로 지정해놓은 URI와 Controller가 mapping됩니다.
- `RequestMapping`에서 `/list.next`와 연결된 `ListController`를 찾습니다.
- `ListController`내의 `excute method`가 실행되어 화면이 만들어지고(list.jsp를 ModelAndView에 담음), 처리된 ModelAndView가 return되어 화면이 보여집니다.

####Prob 7. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
> Servlet Container에 client의 요청이 오면 Service() method가 실행되고, 각 요청에 대한 thread가 만들어집니다. 이 서비스에서, ListController와 ShowController는 최초에 init() method가 실행될 때 단 한번 생성됩니다. 그런데 변수가 method 안이 아닌 바깥 공간에 선언되어서 해당 변수들이 다른 thread와 공유가 됩니다. 그런 경우 문제가 발생할 수 있기 때문에 변수들을 method 안에 선언 해 주는 것이 안전합니다.
