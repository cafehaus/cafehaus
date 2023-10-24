<html>
  <head>
    <title>Hacker News</title>
    <link rel="stylesheet" href="/public/css/news.css" />
  </head>
  <body>
    <ul class="news-view view">
      {% for item in list %}
      <li class="item">
        <a href="{{ item.url }}">文章标题：{{ item.title }}</a>
        <p>{{ helper.relativeTime(item.time) }}</p>
      </li>
      {% endfor %}
    </ul>
  </body>
</html>