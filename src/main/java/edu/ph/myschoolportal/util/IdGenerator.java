package edu.ph.myschoolportal.util;

import edu.ph.myschoolportal.enums.IdPrefix;
import edu.ph.myschoolportal.repository.SmsRoleRepository;
import edu.ph.myschoolportal.repository.SmsUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class IdGenerator {

    private final SmsUserRepository smsUserRepository;
    private final SmsRoleRepository roleRepository;

    public String generateCustomId(String id){
        Integer[] boxedArray = new Integer[]{0, 0, 0, 0, 0, 0};
        String nextGeneratedId = null;
        if(id.equals(IdPrefix.SMS.getId())){
            String prefixId = IdPrefix.SMS.getId();
            String previousId = smsUserRepository.findByUserId();
            nextGeneratedId = this.getGeneratedId(boxedArray, prefixId, previousId);
        } else if(id.equals(IdPrefix.RS.getId())){
            String prefixId = IdPrefix.RS.getId();
            String previousId = roleRepository.findByRoleId();
            nextGeneratedId = this.getGeneratedId(boxedArray, prefixId, previousId);
        }
        return nextGeneratedId;
    }

    private String getGeneratedId(Integer[] boxedArray, String prefixId, String previousId) {
        String nextGeneratedId;
        if (!ObjectUtils.isBlank(previousId)) {
            String idStr = previousId.replace(prefixId, "");
            String[] numberStr = idStr.split("");
            int[] intArray = Stream.of(numberStr).mapToInt(Integer::parseInt).toArray();
            boxedArray = IntStream.of(intArray).boxed().toArray(Integer[]::new);
        }
        nextGeneratedId = this.nextId(boxedArray, prefixId);
        return nextGeneratedId;
    }

    private String nextId(Integer[] boxedArray, String prefixId){
        ArrayList<Integer> digits = new ArrayList<>(Arrays.asList(boxedArray));

        int index = digits.size() - 1;

        while (index >= 0 && digits.get(index) == 9){
            digits.set(index, 0);
            index -= 1;
        }

        if (index < 0){
            digits.set(0, 1);
            digits.add(digits.size(),0);
        } else {
            digits.set(index, digits.get(index) + 1);
        }

        StringBuilder prefixIdBuilder = new StringBuilder(prefixId);
        for (int digit : digits) {
            prefixIdBuilder.append(digit);
        }

        return prefixIdBuilder.toString();
    }
}
