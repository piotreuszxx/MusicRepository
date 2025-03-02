import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistUpdateFormComponent } from './artist-update-form.component';

describe('ArtistUpdateFormComponent', () => {
  let component: ArtistUpdateFormComponent;
  let fixture: ComponentFixture<ArtistUpdateFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArtistUpdateFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtistUpdateFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
