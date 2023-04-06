package egovframework.raw.dto;

import javax.validation.constraints.Null;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@ApiModel(value="SetupDTO", description = "14. Test Set-up Configuraiotn for EUT")
public class SetupDTO {

	@Null
	private MultipartFile image;
	private String title;
}
