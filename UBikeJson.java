package ubike;

/**
 *
 * @author Webb
 */
public class UBikeJson {
    private UBikeJson() {
        throw new AssertionError();
    }
    
    public static final String UBikeRetCode = "retCode";
    
    public static final String UBikeRetMsg = "retMsg";
    
    public static final String UBikeRetVal = "retVal";
    
    public static final String UBikeStop[] = {
      "country_code",
      "area_code",
      "type",
      "status",
      "station_no",
      "name_tw",
      "district_tw",
      "address_tw",
      "name_en",
      "district_en",
      "address_en",
      "name_cn",
      "district_cn",
      "address_cn",
      "parking_spaces",
      "available_spaces",
      "empty_spaces",
      "forbidden_spaces",
      "lat",
      "lng",
      "img",
      "updated_at",
      "time",
    };
}
