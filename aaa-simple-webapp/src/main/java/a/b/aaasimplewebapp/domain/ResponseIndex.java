package a.b.aaasimplewebapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseIndex {
  protected String segment;
  protected String status;
}
