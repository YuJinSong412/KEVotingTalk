package enums;


public enum AlignEnum {
  
  LEFT("left","왼쪽"),
  RIGHT("right","오른쪽");
  
  private final String align;
  private final String title;
  
  AlignEnum(String align, String title) {
    this.align = align;
    this.title = title;
  }

  
  public String getAlign() {
  
    return align;
  }

  
  public String getTitle() {
  
    return title;
  }
  
  
}
