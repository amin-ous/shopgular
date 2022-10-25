import { Component, OnInit, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivitySector } from 'src/app/models/activity-sector.model';
import { ActivitySectorService } from 'src/app/services/activity-sector.service';

@Component({
  selector: 'app-activity-sector',
  templateUrl: './activity-sector.component.html',
  styleUrls: ['./activity-sector.component.css']
})

export class ActivitySectorComponent implements OnInit {

  activitySector!: ActivitySector;
  activitySectors!: ActivitySector[];

  constructor(private activitySectorService: ActivitySectorService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllActivitySectors();
  }

  getAllActivitySectors(): void {
    this.activitySectorService.getAllActivitySectors().subscribe((data: any) => this.activitySectors = data);
  }

  addActivitySector(activitySector: ActivitySector): void {
    this.activitySectorService.addActivitySector(activitySector).subscribe(() => this.getAllActivitySectors());
  }

  updateActivitySector(activitySector: ActivitySector): void {
    this.activitySectorService.updateActivitySector(activitySector).subscribe();
  }

  deleteActivitySector(activitySectorId: number): void {
    this.activitySectorService.deleteActivitySector(activitySectorId).subscribe(() => this.getAllActivitySectors());
  }

  openModal(content: TemplateRef<ActivitySector>, action: ActivitySector | null): void {
    action ? this.activitySector = action : this.activitySector = new ActivitySector();
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result;
  }

}
