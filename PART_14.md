
####1. pom.xml添加webSocket

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

####2. WebSocket管理类

```
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("[webSocket消息] 有新的连接，总数：{}", webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("[webSocket消息] 连接断开，总数：{}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("[webSocket消息] 收到客户端发来的消息：{}", message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            log.info("[webSocket消息] 广播消息，message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```

####3. 创建订单时，发送webSocket消息

```
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    // ...

    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        // ...

        // 发送webSocket消息
        webSocket.sendMessage(orderMaster.getOrderId());


        return orderDTO;
    }
}
```

####4. 前端页面接收webSocket消息

```
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://127.0.0.1:8080/sell/webSocket");
    } else {
        alert('该浏览器不支持webSocket!');
    }
    webSocket.onopen = function (event) {
        console.log("建立连接");
    }

    webSocket.onclose = function (event) {
        console.log("连接关闭");
    }

    webSocket.onmessage = function (event) {
        console.log("收到消息：" + event.data);

        // 强窗提示，播放音乐
        $('#myModal').modal('show');

        document.getElementById('notice').play();
    }

    webSocket.onerror = function () {
        alert("webSocket通信发生错误!");
    }

    window.onbeforeunload = function () {
        webSocket.close();
    }

</script>
```