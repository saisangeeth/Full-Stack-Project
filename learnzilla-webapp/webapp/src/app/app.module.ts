import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatTabsModule} from '@angular/material/tabs';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProgramCreationComponent } from './program-creation/program-creation.component';
import { RouterModule } from '@angular/router';
import {MatChipsModule} from '@angular/material/chips';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatToolbarModule}  from  '@angular/material/toolbar'
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatButtonModule} from '@angular/material/button';
import {HttpClientModule} from '@angular/common/http';
import {MatGridListModule} from '@angular/material/grid-list';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ContentGenerationComponent } from './content-generation/content-generation.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProgramCardComponent } from './program-card/program-card.component';
import {MatCardModule} from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import {MatRadioModule} from '@angular/material/radio'
import { ProgramContainerComponent } from './program-container/program-container.component';
import { SidenavProgramContentComponent } from './sidenav-program-content/sidenav-program-content.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { ProgramVideoPlayerComponent } from './program-video-player/program-video-player.component';
import { EmbedVideo } from 'ngx-embed-video';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatRippleModule} from '@angular/material/core';
import { CourseContainerComponent } from './course-container/course-container.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ContentPageComponent } from './content-page/content-page.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { LandingComponent } from './landing/landing.component';
import { TrainingContentComponent } from './training-content/training-content.component';
import { ProgramDashboardComponent } from './program-dashboard/program-dashboard.component';
import { ContentViewerComponent } from './content-viewer/content-viewer.component';
import { CategorySelectionComponent } from './category-selection/category-selection.component';
import { ProgramDesignComponent } from './program-design/program-design.component';
import { EnrollTraineeComponent } from './enroll-trainee/enroll-trainee.component';
import { ProgramCertificateComponent } from './program-certificate/program-certificate.component';
import { QuestionAnswerSectionComponent } from './question-answer-section/question-answer-section.component';
import { AvatarModule } from 'ngx-avatar';
import { FeedbackDialogComponent } from './feedback-dialog/feedback-dialog.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProgressTestComponent } from './progress-test/progress-test.component';
import { NgCircleProgressModule } from 'ng-circle-progress';
import { LMarkdownEditorModule } from 'ngx-markdown-editor';
import { OtpAuthenticationComponent } from './otp-authentication/otp-authentication.component';
import { MarkdownModule } from 'ngx-markdown';
import { OtpAuthenticationDialogueBoxComponent } from './otp-authentication-dialogue-box/otp-authentication-dialogue-box.component';
import { QuizPlayComponent } from './quiz-play/quiz-play.component';
import { ProgramDetailsComponent } from './program-details/program-details.component';


@NgModule({
  declarations: [
    AppComponent,
    ProgramCreationComponent,
    ContentGenerationComponent,
    NavBarComponent,
    LoginComponent,
    RegisterComponent,

    ProgramCardComponent,
    ProgramContainerComponent,
    SidenavProgramContentComponent,
    ProgramVideoPlayerComponent,
    CourseContainerComponent,
    SidebarComponent,
    ContentPageComponent,
    LandingComponent,
    TrainingContentComponent,
    ProgramDashboardComponent,
    ContentViewerComponent,
    CategorySelectionComponent,
    ProgramDesignComponent,
    EnrollTraineeComponent,
    ProgramCertificateComponent,
    QuestionAnswerSectionComponent,
    FeedbackDialogComponent,
    ProgressTestComponent,
    OtpAuthenticationComponent,
    OtpAuthenticationDialogueBoxComponent,
    QuizPlayComponent,
    ProgramDetailsComponent,

  ],
  imports: [
    
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    HttpClientModule,
    MatGridListModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    RouterModule,
    MatChipsModule,
    MatAutocompleteModule,
    MatTabsModule,
    MatCardModule,
    MatDialogModule,
    MatRadioModule,
    MatTooltipModule,
    EmbedVideo.forRoot(),
    MatSidenavModule,
    MatListModule,
    MatMenuModule,
    MatButtonToggleModule,
    MatExpansionModule,
    MatRippleModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    BrowserAnimationsModule,
    NgbModule,
    LMarkdownEditorModule,

    AvatarModule,
    NgCircleProgressModule.forRoot({
      // set defaults here
      "radius": 60,
      "outerStrokeWidth": 10,
      "innerStrokeWidth": 5,
      "showBackground": false,
      "startFromZero": false
    }),
    MarkdownModule.forRoot(),

  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [CategorySelectionComponent,  FeedbackDialogComponent]
})
export class AppModule { }
