# AnnotationLayout
根据Data自动生成页面View框架

### Quict Start

* 使用`fillView`填充View

  ```java
  public class UserInfoActivity extends AutoActivity {

      @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          fillView(Mocks.mockUserInfo());
      }

  }
  ```

* 声明实体类

  ```java
  @Layout(LayoutType.Linear)
  public class UserInfo {

      @Label("用户名: %s")
      public String name;
      @Label("地址: %s")
      public String address;
      @Order(-1)
      @Label("年龄: %d")
      public int age;
      @Layout(LayoutType.Linear)
      public List<Goods> goods;

      @Layout(LayoutType.Linear)
      public static class Goods {
          @Label("价格: %.2f")
          public float price = 3.1f;
          @Label("名称: %s")
          public String name = "呢大衣";
          @Label("描述: %s")
          public String desc = "秋冬换季百搭";
      }

  }
  ```

  ​

