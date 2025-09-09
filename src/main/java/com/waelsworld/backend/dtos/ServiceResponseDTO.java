package com.waelsworld.backend.dtos;

import com.waelsworld.backend.models.Invoice;
import com.waelsworld.backend.models.WorkLog;
import com.waelsworld.backend.models.enums.ServiceStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceResponseDTO {
    private UUID vehicleId;
    private UUID serviceCentreId;
    private String status;
    private ServiceStatus serviceStatus;
    private LocalDateTime serviceDate;
    private List<WorkLog> workLogs;
    private Invoice invoice;
    private UUID mechanicId;
}
