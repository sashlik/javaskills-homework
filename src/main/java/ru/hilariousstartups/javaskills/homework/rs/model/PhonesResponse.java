/*
 * OpenAPI definition
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package ru.hilariousstartups.javaskills.homework.rs.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
/**
 * PhonesResponse
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-04-17T19:11:39.886977+03:00[Europe/Moscow]")
public class PhonesResponse {
  @JsonProperty("phones")
  private List<String> phones = null;

  public PhonesResponse phones(List<String> phones) {
    this.phones = phones;
    return this;
  }

  public PhonesResponse addPhonesItem(String phonesItem) {
    if (this.phones == null) {
      this.phones = new ArrayList<String>();
    }
    this.phones.add(phonesItem);
    return this;
  }

   /**
   * Get phones
   * @return phones
  **/
  @Schema(description = "")
  public List<String> getPhones() {
    return phones;
  }

  public void setPhones(List<String> phones) {
    this.phones = phones;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhonesResponse phonesResponse = (PhonesResponse) o;
    return Objects.equals(this.phones, phonesResponse.phones);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phones);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhonesResponse {\n");
    
    sb.append("    phones: ").append(toIndentedString(phones)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
