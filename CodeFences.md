### here's a block of code with a title:

```py title="My Code Block"
print('this is code')
```

```java title="My Code Block"
class TheClass {
    String s = "this is code";
}
```

```yaml {"color: #333; background: #f8f8f8;" }
A code block with inline styles. Fancy!
```

Set multiple lines of code in fenced code blocks.

```
action: function(ctx) {
    workflow.check(!ctx.issue.isChanged('votes'), workflow.i18n('Voting for a resolved issue is not allowed.'));
},
```

```yaml
theme:
  features:
    - content.code.annotate # (1)
```

```py linenums="1"
def bubble_sort(items):
    for i in range(len(items)):
        for j in range(len(items) - 1 - i):
            if items[j] > items[j + 1]:
                items[j], items[j + 1] = items[j + 1], items[j]
```

```py hl_lines="2 3  linenums="1"
def bubble_sort(items):
    for i in range(len(items)):
        for j in range(len(items) - 1 - i):
            if items[j] > items[j + 1]:
                items[j], items[j + 1] = items[j + 1], items[j]
```

```java title="bubble_sort.py
interface Inter {
    static void main(String[] args) {
    }
}
```

```java
    private class TheClass {
    String s = "<h1>test</h1>";
}
```

