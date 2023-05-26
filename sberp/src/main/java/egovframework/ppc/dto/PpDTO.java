package egovframework.ppc.dto;

import java.util.List;
import egovframework.ppc.service.Pp;
import egovframework.ppc.service.PpHis;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class PpDTO extends Pp {

  @ApiModelProperty(value="사전통관 ID ", example = "")
  String ppId;
  
  @ApiModelProperty(value="메모리스트 ", example = "", hidden = true)
  List<PpHis> memoList;
}
