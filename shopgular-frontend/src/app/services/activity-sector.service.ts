import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivitySector } from '../models/activity-sector.model';

@Injectable({
  providedIn: 'root'
})

export class ActivitySectorService {

  readonly API_URL = 'http://localhost:8089/activity-sector';

  constructor(private httpClient: HttpClient) { }

  addActivitySector(activitySector: ActivitySector) {
    return this.httpClient.post(`${this.API_URL}/add-activity-sector`, activitySector);
  }

  getAllActivitySectors() {
    return this.httpClient.get(`${this.API_URL}/get-all-activitiy-sectors`);
  }

  updateActivitySector(activitySector: ActivitySector) {
    return this.httpClient.put(`${this.API_URL}/update-activity-sector`, activitySector);
  }

  deleteActivitySector(activitySectorId: number) {
    return this.httpClient.delete(`${this.API_URL}/delete-activity-sector/${activitySectorId}`);
  }

}
